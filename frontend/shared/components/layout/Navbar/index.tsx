const Navbar = () => {
  return (
    <nav className="navbar-container navbar-expand-md navbar bg-primary">
      <div className="navbar-menu-container container-fluid">
        <a href="link" className="navbar-logo-text">
          <h4>Shop Catalog</h4>
        </a>
        <div className="navbar-menu-list collapse navbar-collapse">
          <ul className="navbar-nav offset-md-2">
            <li>
              <a href="link">HOME</a>
            </li>
            <li>
              <a href="link">CATÁLOGO</a>
            </li>
            <li>
              <a href="link">ADMIN</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
