export const Heroes = () => {
  return (
    <div>
      <div className="d-none d-lg-block">
        <div className="container mt-5">
          <div className="row g-0">
            <div className="col-md-6">
              <div className="col-image-left">
                {/* Image or content for left column */}
              </div>
            </div>
            <div className="col-md-6 d-flex justify-content-center align-items-center">
              <div className="container ml-2">
                <h1>What are you reading?</h1>
                <p className="lead">We want to know what you are reading?</p>
                <a className="btn main-color btn-lg text-white" href="#">
                  Sign up
                </a>
              </div>
            </div>
          </div>
        </div>

        <div className="container mt-5">
          <div className="row g-0">
            <div className="col-md-6 d-flex justify-content-center align-items-center">
              <div className="container ml-2">
                <h1>Our collection is always changing</h1>
                <p className="lead">Try to get updated</p>
              </div>
            </div>
            <div className="col-md-6">
              <div className="col-image-right">
                {/* Image or content for right column */}
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Mobile view */}
      <div className="d-lg-none">
        <div className="container mt-5">
          <div className="col-image-left">
            {/* Image or content for mobile view */}
          </div>
          <div className="mt-2">
            <h1>What are you reading?</h1>
            <p className="lead">We want to know what you are reading?</p>
          </div>
        </div>
      </div>
    </div>
  );
};
