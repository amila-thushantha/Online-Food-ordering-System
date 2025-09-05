import { Card, Chip, IconButton } from '@mui/material'
import React from 'react'
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';


//Card will come from metiriel ui
const RestaurantCart = () => {
  return (
    <div>
        
      <Card className='w-[18rem]'>

        <div className={`${true? 'cursor-pointer' : 'cursor-not-allowed'} relative`}>
        <img className='w-full h-[10rem] rounded-t-md object-cover'
        src="https://cdn.pixabay.com/photo/2020/08/27/07/31/restaurant-5521372_1280.jpg" alt=""/>
        <Chip
        size='small'
        className='absolute top-2 left-2'
        color={true? "success" : "error"}
        label={true? "Open" : "Closed"}
        />
        </div>

        <div className='p-4 textPart lg:flex w-full justify-between'>
            <div className='space-y-1'>
                <p className='font-semibold text-lg'>
                    Shashaa
                </p>
                <p className='text-gray-400 text-sm'>
                    Carving it all? Dive into our global fla...
                </p>

            </div>

            <div>
                <IconButton>
                        {true? <FavoriteIcon/> : <FavoriteBorderIcon/>}
                </IconButton>
            </div>
            

        </div>
        
            
        
      </Card>
    </div>
  )
}

export default RestaurantCart
