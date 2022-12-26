import "bootstrap/js/src/collapse.js";

const Navbar = () => {
  return (
    <nav
      className="
    navbar-container
    navbar-expand-md
    navbar
    bg-primary"
    >
      <div className="navbar-menu-container navbar-dark container-fluid">
        <a href="link" className="navbar-logo-text">
          <h4>Shop Catalog</h4>
        </a>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#catalog-navbar"
          aria-controls="catalog-navbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div
          className="navbar-menu-list collapse navbar-collapse"
          id="catalog-navbar"
        >
          <ul className="navbar-nav offset-md-2 main-menu-list">
            <li>
              <a href="link" className="active">
                HOME
              </a>
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
