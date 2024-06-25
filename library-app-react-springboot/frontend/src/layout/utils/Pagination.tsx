import React from "react";

const Pagination: React.FC<{
  currentPage: number;
  totalPage: number;
  paginate: (pageNumber: number) => void;
}> = (props) => {
  const pageNumber = [];

  if (props.currentPage === 1) {
    pageNumber.push(props.currentPage);
    if (props.totalPage >= props.currentPage + 1) {
      pageNumber.push(props.currentPage + 1);
      if (props.totalPage >= props.currentPage + 2) {
        pageNumber.push(props.currentPage + 2);
      }
    }
  } else if (props.currentPage > 1) {
    if (props.currentPage >= 3) {
      pageNumber.push(props.currentPage - 2);
      pageNumber.push(props.currentPage - 1);
    } else {
      pageNumber.push(props.currentPage - 1);
    }
    pageNumber.push(props.currentPage);

    if (props.totalPage >= props.currentPage + 1) {
      pageNumber.push(props.currentPage + 1);
    }

    if (props.totalPage >= props.currentPage + 2) {
      pageNumber.push(props.currentPage + 2);
    }
  }

  return (
    <nav aria-label="Pagination">
      <ul className="pagination">
        <li className="page-item" onClick={() => props.paginate(1)}>
          <button className="page-link">First Page</button>
        </li>
        {pageNumber.map((number) => (
          <li
            key={number}
            onClick={() => props.paginate(number)}
            className={
              "page-item" + (props.currentPage === number ? " active" : "")
            }
          >
            <button className="page-link">{number}</button>
          </li>
        ))}
        <li
          className="page-item"
          onClick={() => props.paginate(props.totalPage)}
        >
          <button className="page-link">Last Page</button>
        </li>
      </ul>
    </nav>
  );
};

export default Pagination;
