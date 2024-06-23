const Navbar = () => {
  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark main-color py-2">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            Library app
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown"
            aria-expanded="false"
            aria-label="Toggle Navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNavDropdown">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Home
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Search book
                </a>
              </li>
            </ul>
            <ul className="navbar-nav">
              <li className="nav-item m-1">
                <a className="btn btn-outline-light" href="#">
                  Sign in
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
