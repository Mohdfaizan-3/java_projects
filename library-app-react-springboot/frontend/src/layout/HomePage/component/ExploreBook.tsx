import { Link } from "react-router-dom";

export const ExploreBook = () => {
  return (
    <div className="p-5 m-0 bg-dark header">
      <div className="container-fluid py-5 text-white d-flex justifiy-content-center align-items-center">
        <div>
          <h1 className="display-5 fw-bold">Find the next adventure</h1>
          <p className="col-md-8 fs-4">Where would you like to go first ?</p>
          <Link to="/search" type="button" className="btn main-color btn-lg text-white">
            Explore top books
          </Link>
        </div>
      </div>
    </div>
  );
};
