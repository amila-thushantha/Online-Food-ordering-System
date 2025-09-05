import React from 'react'
// Import css files cerousel
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from 'react-slick';
import CerouselItem from './CerouselItem';
import { topMeels } from './topMeels';

const MultiitemCerousel = () => {
    var settings = {
        dots: true,
        infinite: true,
        speed: 700,
        slidesToShow: 5,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        arrows: true
      };
  return (
    <div>
      <Slider {...settings}>
        {topMeels.map((item)=><CerouselItem 
        image={item.image} 
        title={item.title}
        />)}
      </Slider>
    </div>
  )
}

export default MultiitemCerousel
