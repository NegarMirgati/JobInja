import React, { Component } from 'react'

export default class MainSearch extends Component {
  render() {
    return (
      <div>
        <div className="search-container">
            <form action="">
                <button type="submit"><i className=""></i>جستجو</button>
                <input type="text" placeholder="جست و جو در جاب اونجا" name="search"/>
            </form>
        </div>
      </div>
    )
  }
}
