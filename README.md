# **NASA API Picture of the day app**

## **A little bit about this project**

It's a simple Android application written in Kotlin.
Application uses one of the most popular APIs - NASA Astronomy Picture of the Day (APOD) (https://api.nasa.gov/)

There are two options to view the picture:
* Today APOD
* Random APOD (for simplicity I limited values to between 2015 and 2018 and used up to 28 days for each month)

Min Android version 24

## **Validating API requests**

It will work as is, but there is a limit for API key (`DEMO_KEY`) provided, which is:

* 30 requests per IP address per hour
* 50 requests per IP address per day

It is recommended to register. New key should be added to `app/credentials.gradle` file

## **Improvement ideas**

1. I don't show what date is random :-(
2. One of the image details in the request come back as URL and it takes some time to render it in ImageView. While It's
loading, I didn't use any placeholder or skeletom library to indicate it. There is an Android Progress Bar to indicate the
API request
3. There is no caching or database
4. Espresso tests could be improved by adding a way to match drawable in ImageView. I tried using Android KTX from
one of the blog posts online, but it didn't work. This is considered as work in progress


