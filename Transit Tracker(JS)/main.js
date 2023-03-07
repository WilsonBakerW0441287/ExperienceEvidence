(function () { //IIFE - Immediately Invoked Function Expression

    //create map in leaflet and tie it to the div called 'theMap'
    const map = L.map('theMap').setView([ 44.650627, -63.597140 ], 14);
    //add a tile layer to the map
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    
    
    let busmarkers;

    function fetchBusData() {
        fetch("https://prog2700.onrender.com/hrmbuses")
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                //first filter the data to only include buses with routeId between 1 and 10
                const filteredData = data.entity.filter(
                    (bus) =>
                        bus.vehicle.trip && //if it has a trip at all
                        bus.vehicle.trip.routeId >= 1 && //and the routeId is between 1 and 10
                        bus.vehicle.trip.routeId <= 10
                );
                console.log(filteredData);
                //then map the data to a new array of objects with the properties we want
                const busFeatures = filteredData.map((bus) => ({
                    type: "Feature",
                    geometry: {
                        type: "Point",
                        coordinates: [
                            bus.vehicle.position.longitude,
                            bus.vehicle.position.latitude,
                        ],
                    },
                    properties: {
                        id: bus.id,
                        label: bus.vehicle.vehicle.label,
                        routeId: bus.vehicle.trip.routeId,
                        timestamp: bus.vehicle.timestamp,
                    },
                }));
                console.log(busFeatures);
                //make sure the busmarkers layer group exists and clear it if needed
                if (busmarkers) {
                    busmarkers.clearLayers();
                } else {
                    //otherwise add the layer group to the map
                    busmarkers = L.featureGroup().addTo(map);
                }
                //add the bus markers to the map using GeoJSON
                L.geoJSON(busFeatures, {
                    //create a custom icon for each bus
                    pointToLayer: (feature, latlng) => {
                        const label = L.marker(latlng, {
                            icon: L.divIcon({
                                className: "bus-label",
                                html: feature.properties.label,
                            }),
                        });
                        const marker = L.marker(latlng, {
                            icon: L.icon({
                                iconUrl: "bus.png",
                                iconSize: [ 38, 38 ],
                                iconAnchor: [ 19, 19 ],
                            }),
                        });
                        //add a popup to each marker
                        marker.bindPopup(
                            `Bus ${ feature.properties.id } on Route ${ feature.properties.routeId }<br>Last Updated: ${ new Date(
                                feature.properties.timestamp * 1000
                            ).toLocaleString() }`
                        );
                        //return the marker and label as a layer group
                        return L.layerGroup([ marker, label ]);
                    },
                    //add the layer group to the busmarkers layer group
                }).addTo(busmarkers);
            })
            .catch((error) => console.log(error));
    }
    //call the function to fetch the data and add the markers to the map
    fetchBusData();
    //call the function every 15 seconds
    setInterval(() => {
        fetchBusData();
    }, 15000);

})()