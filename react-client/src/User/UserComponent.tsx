import React, { Component } from 'react'
import profilePhoto from 'src/Assets/images/dibi.jpeg'
export default class UserComponent extends Component {
  render() {
    document.body.classList.add('htmlBodyStyle');
    return (
      <div>
        <div className= "container-fluid main">
          <div className="row" id = "picRow">
            <div className = "col-sm-12">
              <div className="blue-box"><br></br> <br></br> <br></br> <br></br> <br></br></div>
                <div className = "text-right">
                  <p className = "profile-name"> دیبی </p>
                  <p className = "profile-job"> خوار خوار خوارم من </p>
                </div>
              <div>
                <img  src={profilePhoto} className="img-rounded profile-photo img-responsive " alt="image"/>
                  <div className = "rectangle"></div>
                  <div className = "rectangle-2"></div>
                  <div className = "rectangle-3"></div>
                  <div id = "parallelogram-1"></div>
                  <div id = "parallelogram-2"></div>
              </div>
            </div>
          </div>
          <div className = "row">
            <div className = "col-sm-12">
              <p className = "rtl-text"> لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.
          چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. کتابهای زیادی در شصت و سه درصد گذشته، حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.
            </p>
          </div>
          </div>

        <div className = "row justify-content" id = "skill-row">
            <div className = "col-sm-12">
              <div id = "add-skill-name">
                <p>  :مهارت ها </p>
              </div>
              <div className = "add-skill-form-container">
                <form className = "add-skill-form">
                  <button type = "button" className = "add-skill-button" > افزودن مهارت </button>
                    <select className =  "add-skill-input" placeholder ="--انتخاب مهارت--">
                      <option>  --انتخاب مهارت--  </option>
                      <option value="Python">Python</option>
                      <option value="Java">Java</option>
                      <option value="Database">Database</option>
                    </select>
                </form>
              </div>
            </div>
          </div>

        <div className = "row justify-content-end skills">
          <div className = "col">
            <div>
            <button type="button" className="btn skill-btn">
            HTML <span className="badge badge-blue">5</span>
            </button>
            <button type="button" className="btn skill-btn">
            CSS <span className="badge badge-blue">3</span>
            </button>
            <button type="button" className="btn skill-btn">
            JavaScript <span className="badge badge-blue">16</span>
            </button>
            <button type="button" className="btn skill-btn">
            TypeScript <span className="badge badge-red">-</span>
            </button>
          </div>
          <div className = "col"></div>
        </div>
      </div>

    </div>   
    </div>
    )
  }

}
