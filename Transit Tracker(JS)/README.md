## Transit Tracker

### Summary
Create a solution that will do the following
* Display a map in the browser. 
* Fetch real-time transit data information data from a publicly available API. (Bus data)
* Filter the raw data to a subset based on specified criteria.
* Convert the filtered API data into GeoJSON format.
* Using the GeoJSON data, plot markers on the map to display the current position of vehicles.
* Add functionality that will cause the map to auto refresh after a certain interval of time.
* Customize the vehicle display markers.
## General Requirements
### Display a Geographical Map
 you will be working with the [Leaflet.js](https://leafletjs.com/) mapping library. Leaflet is a leading open-source JavaScript library for mobile-friendly interactive maps. It provides an easy-to-use programming API for customizing and building various types of maps.
### Fetch Real-time transit data - Real-time bus data
[Halifax Transit open data](https://www.halifax.ca/home/open-data/halifax-transit-open-data), Halifax Transit has launched the General Transit Feed Specification (GTFS) open data feed to developers as a beta release. This data is used by Google and other third-party developers to create apps for use by the public.

This API endpoint will return real-time data for all buses currently in service throughout HRM. Your application will need to fetch this data in its raw form and be able to filter the results according to the following criteria.

* Filter requirement: Filter the resulting data so that you keep buses on routes 1-10 only.

### Convert raw API data into GeoJSON format
Leaflet supports and works well with the [GeoJSON](http://geojson.org/) data format.

### Apply code to auto-refresh the map

After fifteen (15) seconds, re-fetch the updated API data and re-perform the GeoJSON transformation as necessary.
<img src="https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/Transit%20Tracker(JS)/Example.png"/>
