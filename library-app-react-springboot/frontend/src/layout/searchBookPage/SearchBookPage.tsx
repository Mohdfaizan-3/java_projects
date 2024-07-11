import { useEffect, useState } from "react";
import BookModel from "../../models/BookModel";
import Pagination from "../utils/Pagination";
import { SearchBook } from "./components/SearchBook";

export const SearchBookPage = () => {
  const [books, setBooks] = useState<BookModel[]>([]);
  const [loading, setLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [booksPerPage] = useState(5);
  const [totalAmountOfBooks, setTotalAmountOfBooks] = useState(0);
  const [totalPage, setTotalPage] = useState(0);
  const [search, setSearch] = useState("");
  const [searchUrl, setSearchUrl] = useState("");
  const [category, setCategory] = useState("book selection");

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const baseUrl: string = "http://localhost:8080/api/books";

        let url: string = "";

        if (searchUrl === "") {
          url = `${baseUrl}?page=${currentPage - 1}&size=${booksPerPage}`;
        } else {
          let page = searchUrl.replace('<pageNumber>', `${currentPage-1}`);
          url = baseUrl + page;
        }
        const response = await fetch(url);

        if (!response.ok) {
          throw new Error("Something went wrong!");
        }

        const responseJson = await response.json();
        const data = responseJson._embedded.books;
        setTotalAmountOfBooks(responseJson.page.totalElements);
        setTotalPage(responseJson.page.totalPages);

        const loadedBooks: BookModel[] = data.map((book: any) => ({
          id: book.id,
          title: book.title,
          author: book.author,
          description: book.description,
          copies: book.copies,
          copiesAvailable: book.copiesAvailable,
          category: book.category,
          img: book.img,
        }));

        setBooks(loadedBooks);
        setLoading(false);
      } catch (error: any) {
        setLoading(false);
        setHttpError(error.message);
      }
    };

    fetchBooks();
    window.scrollTo(0, 0);
  }, [currentPage, searchUrl, booksPerPage]);

  if (loading) {
    return <div className="container m-5">Loading....</div>;
  }

  if (httpError) {
    return <div className="container m-5">{httpError}</div>;
  }

  const handleSearch = () => {
    setCurrentPage(1);
    if (search === "") {
      setSearchUrl("");
    } else {
      setSearchUrl(
        `/search/findByTitleContaining?title=${search}&page=<pageNumber>&size=${booksPerPage}`
      );
    }
    setCategory("book category");
  };

  const setCategoryField = (value : string) => {
    setCurrentPage(1);
    if (
      value.toLowerCase() === "fe" ||
      value.toLowerCase() === "be" ||
      value.toLowerCase() === "data" ||
      value.toLowerCase() === "devops"
    ) {
      setCategory(value.toLowerCase());
      setSearchUrl(`/search/findByCategory?category=${value.toLowerCase()}&page=<pageNumber>&size=${booksPerPage}`);
    } else {
      setSearchUrl("");
    }
  }

  const indexOfFirstBook: number = currentPage * booksPerPage - booksPerPage;
  // const lastItem =
  //   booksPerPage * currentPage <= totalAmountOfBooks
  //     ? booksPerPage * currentPage
  //     : totalAmountOfBooks;
  const lastItem =
    currentPage !== totalPage
      ? indexOfFirstBook + booksPerPage
      : totalAmountOfBooks;

  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  return (
    <div>
      <div className="container">
        <div>
          <div className="row mt-5">
            <div className="col-6">
              <div className="d-flex">
                <input
                  className="form-control me-2"
                  type="search"
                  placeholder="search"
                  aria-labelledby="search"
                  onChange={(e) => setSearch(e.target.value)}
                />
                <button
                  className="btn btn-outline-success"
                  onClick={() => handleSearch()}
                >
                  Search
                </button>
              </div>
            </div>
            <div className="col-4">
              <div className="dropdown">
                <button
                  className="btn btn-secondary dropdown-toggle"
                  type="button"
                  id="dropdownMenuBtn1"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  {category}
                </button>
                <ul
                  className="dropdown-menu"
                  aria-labelledby="dropdownMenuBtn1"
                >
                  <li onClick={() => setCategoryField("All")}>
                    <a className="dropdown-item" href="#">
                      All
                    </a>
                  </li>
                  <li onClick={() => setCategoryField("FE")}>
                    <a className="dropdown-item" href="#">
                      Frontend
                    </a>
                  </li>
                  <li onClick={() => setCategoryField("BE")}>
                    <a className="dropdown-item" href="#">
                      Backend
                    </a>
                  </li>
                  <li onClick={() => setCategoryField("Data")}>
                    <a className="dropdown-item" href="#">
                      Data
                    </a>
                  </li>
                  <li onClick={() => setCategoryField("Devops")}>
                    <a className="dropdown-item" href="#">
                      DevOps
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          {totalAmountOfBooks > 0 ? (
            <>
              <div className="mt-3">
                <h5>Number of results : ({totalAmountOfBooks})</h5>
              </div>
              <p>
                {indexOfFirstBook + 1} to {lastItem} of {totalAmountOfBooks}{" "}
                items
              </p>
              {books.map((book) => (
                <SearchBook book={book} key={book.id} />
              ))}
            </>
          ) : (
            <>
              <div className="m-3">
                <h3>cannot find the result</h3>
                <a href="#" type="button" className="btn-primary">
                  Service
                </a>
              </div>
            </>
          )}

          {totalPage > 1 && (
            <Pagination
              currentPage={currentPage}
              totalPage={totalPage}
              paginate={paginate}
            />
          )}
        </div>
      </div>
    </div>
  );
};
