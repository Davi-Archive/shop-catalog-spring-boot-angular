package io.davi.catalog.services;

import io.davi.catalog.dto.RoleDTO;
import io.davi.catalog.dto.UserDTO;
import io.davi.catalog.dto.UserInsertDTO;
import io.davi.catalog.dto.UserUpdateDTO;
import io.davi.catalog.entities.Role;
import io.davi.catalog.entities.User;
import io.davi.catalog.repositories.RoleRepository;
import io.davi.catalog.repositories.UserRepository;
import io.davi.catalog.services.exceptions.DatabaseException;
import io.davi.catalog.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();

        List<UserDTO> listDto = list.stream()
                .map(x -> new UserDTO(x))
                .collect(Collectors.toList());

//        List<UserDTO> listDto = new ArrayList<>();
//        for(User cat:list){
//            listDto.add(new UserDTO());
//        }
        return list.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }


    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashedPassword);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        for (RoleDTO roleDto : dto.getRoles()) {
            Role role = roleRepository.getOne(roleDto.getId());
            entity.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            logger.error("User not found: " + username);
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }
}
