import { useEffect, useState } from "react";
import BookModel from "../../models/BookModel";
import { SearchBook } from "./components/SearchBook";

export const SearchBookPage = () => {
  const [books, setBooks] = useState<BookModel[]>([]);
  const [loading, setLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(() => {
    const fetchBooks = async () => {
      const baseUrl: string = "http://localhost:8080/api/books";
      const url: string = `${baseUrl}?page=0&size=5`;
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error("something went wrong");
      }
      const responseJson = await response.json();
      const data = await responseJson._embedded.books;
      const loadedBooks: BookModel[] = [];
      for (const key in data) {
        loadedBooks.push({
          id: data[key].id,
          title: data[key].title,
          author: data[key].author,
          description: data[key].description,
          copies: data[key].copies,
          copiesAvailable: data[key].copiesAvailable,
          category: data[key].category,
          img: data[key].img,
        });
      }
      setBooks(loadedBooks);
      setLoading(false);
    };
    fetchBooks().catch((error: any) => {
      setLoading(false);
      setHttpError(error.message);
    });
  }, []);

  if (loading) {
    return <div className="container m-5">Loading....</div>;
  }

  if (httpError) {
    return <div className="container m-5">{httpError}</div>;
  }

  return <div>
    <div className="container">
        <div>
            <div className="row mt-5 ">
                <div className="col-6">
                    <div className="d-flex">
                        <input className="form-control me-2" type="search" placeholder="search" aria-labelledby="search" />
                        <button className="btn btn-outline-success">Search</button>
                    </div>
                </div>
                <div className="col-4">
                    <div className="dropdown">
                        <button className="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuBtn1" data-bs-toggle="dropdown" aria-expanded="false">Category</button>
                        <ul className="dropdown-menu" aria-labelledby="dropdownMenuBtn1">
                            <li>
                                <a className="dropdown-item" href="#">
                                    ALl
                                </a>
                            </li>
                            <li>
                                <a className="dropdown-item" href="#">
                                    Frontend
                                </a>
                            </li><li>
                                <a className="dropdown-item" href="#">
                                    Backend
                                </a>
                            </li><li>
                                <a className="dropdown-item" href="#">
                                    Data
                                </a>
                            </li><li>
                                <a className="dropdown-item" href="#">
                                    Devops
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div className="mt-3">
                <h5>Number of results : (22)</h5>
            </div>
            <p>1 to 5 of 22 items</p>
            {
                books.map(book => (
                    <SearchBook book={book} key={book.id}/>
                )) 
            }
        </div>
    </div>
  </div>;
};
