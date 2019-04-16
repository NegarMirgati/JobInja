import React, { Component } from 'react'
import slide5 from 'src/Assets/images/slide5.jpg';

export default class SearchUsers extends Component {
  render() {
    return (
      <div>
        <div className = "row">
          <div className = "col">
            <div className = "row">
              <div className = "search-container-user">
                <form action = "">
                  <input type="text" placeholder="جستجو نام کاربر" name="search"/>
                </form>
              </div>
            </div>
            <div className = "row">
              <div className = "search-user">
                <div className = "col">
                  <img className="img-rounded  search-image img-rounded img-responsive" src={slide5} alt = "image" />
                </div>
                <div className = "col">
                  <p className = "search-user-name"> آقای مجری</p>
                  <p className = "search-user-job">مجری</p>
                </div>
            </div>
          </div>
          <div className = "row">
              <div className = "search-user">
                <div className = "col">
                  <img className="img-rounded  search-image img-rounded img-responsive" src={slide5} alt = "image" />
                </div>
                <div className = "col">
                  <p className = "search-user-name">داداش گلم</p>
                  <p className = "search-user-job">نوازنده</p>
                </div>
            </div>
          </div>
          <div className = "row">
              <div className = "search-user">
                <div className = "col">
                  <img className="img-rounded  search-image img-rounded img-responsive" src={slide5} alt = "image" />
                </div>
                <div className = "col">
                <p className = "search-user-name">جیگر</p>
                  <p className = "search-user-job">اسب</p>
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    )
  }
}
