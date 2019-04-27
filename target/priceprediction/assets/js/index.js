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
});