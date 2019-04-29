/**
 * 
 */
jQuery(document).ready(function($){
	var input = $('#input-a');
	input.clockpicker({
	    autoclose: true
	});
	$('body').on("click", '#predict', function(){
		getEstimate();
	});
	function getEstimate(){
		var pickup_latitude = $('#pickup_latitude').val();
		var pickup_longitude = $('#pickup_longitude').val();
		var drop_latitude = $('#drop_latitude').val();
		var drop_longitude = $('#drop_longitude').val();
		var date = $('#datepicker').val();
		var pass = $('#pass_count').val();
		var time = $('#input-a').val();
		console.log(pickup_latitude);
		console.log(pickup_longitude);
		console.log(drop_latitude);
		console.log(drop_longitude);
		console.log(date);
		console.log(pass);
		console.log(time);
		
		var dataObj = JSON.stringify({
	    	 'pickup_latitude': pickup_latitude,
	    	 'pickup_longitude': pickup_longitude,
	    	 'drop_latitude': drop_latitude,
	    	 'drop_longitude':drop_longitude,
	    	 'passenger_count': pass,
	    	 'date': date,
	    	 'time': time+":00",
	    });
		var url = 'webapi/price/get_estimate';
	    $.ajax({
		      type : 'POST',
			  contentType : 'application/json',
			  url : url,
			  data : dataObj,
	          success: function(data) {
	            //alert(data);
	            console.log("Success");
	            $("#price").text(data); 
	          },
	          error: function(data){
	          	console.log(data);
	          }
	    });
	        
		$("html, body").animate({ scrollTop: $(document).height() }, 1000);
	}
	
	function setUpClickListener(map) {
		  // Attach an event listener to map display
		  // obtain the coordinates and display in an alert box.
		  map.addEventListener('tap', function (evt) {
			  var coord = map.screenToGeo(evt.currentPointer.viewportX,
					evt.currentPointer.viewportY);
		      var lat = coord.lat.toFixed(4);
		      var lon = coord.lng.toFixed(4);
		      if($('#pickup_latitude').val()=="" && $('#pickup_longitude').val()==""){
		    	  $('#pickup_latitude').val(lat);
		    	  $('#pickup_longitude').val(lon);
		    	  console.log("Pickup set");
		      }
		      else{
		    	  $('#drop_latitude').val(lat);
		    	  $('#drop_longitude').val(lon);
		    	  console.log("Drop set");
		      }
		      alert('Clicked at ' + lat +' ' + lon);
		  });
	}
	
	/**
	* Boilerplate map initialization code starts below:
	*/

	//Step 1: initialize communication with the platform
	var platform = new H.service.Platform({
		app_id: 'devportal-demo-20180625',
		app_code: '9v2BkviRwi9Ot26kp2IysQ',
		useHTTPS: true
	});
	
	var pixelRatio = window.devicePixelRatio || 1;
	var defaultLayers = platform.createDefaultLayers({
		tileSize: pixelRatio === 1 ? 256 : 512,
	    ppi: pixelRatio === 1 ? undefined : 320
	});

	//Step 2: initialize a map
	var map = new H.Map(document.getElementById('map'), defaultLayers.normal.map,{
		center: {lat: 40.7141667, lng:-74.0063889 },
		zoom: 12,
		pixelRatio: pixelRatio
	});

	//Step 3: make the map interactive
	// MapEvents enables the event system
	// Behavior implements default interactions for pan/zoom (also on mobile touch environments)
	var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

	setUpClickListener(map);
});