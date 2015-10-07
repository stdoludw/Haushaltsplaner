<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    google.load("visualization", "1.1", {"packages":["corechart", "bar", "table", "line"]});
      google.setOnLoadCallback(drawChart);
      
      
//#######################################################################################################################

	 access bean=(access)request.getAttribute("bean");  
Vector<Object> tmp = bean.getStatistik();

//#######################################################################################################################

      function drawChart() {
    	    	  
//#######################################################################################################################
        var data = google.visualization.arrayToDataTable([
          ['Monat', 'Ausgaben'],
          ['Januar - Maerz', parseInt(tmp[0])],
          ['April - Juni', parseInt(tmp[1])],
          ['Juli - September', parseInt(tmp[2])],
          ['Oktober - Dezember', parseInt(tmp[3])],
        ]);

        var options = {
          title: 'Ausgaben pro Quartal',
          is3D: true,
        };
        
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);

          var data2 = google.visualization.arrayToDataTable([
              ['Produktname', 'Anzahl'],
              [tmp[4], parseInt(tmp[5])], [tmp[6], parseInt(tmp[7])], [tmp[8], parseInt(tmp[9])],
              [tmp[10], parseInt(tmp[11])], [tmp[12], parseInt(tmp[13])], [tmp[14], parseInt(tmp[15])],
              [tmp[16], parseInt(tmp[17])], [tmp[18], parseInt(tmp[19])], [tmp[20], parseInt(tmp[21])],
              [tmp[21], parseInt(tmp[22])]
            ]);
   
            var options2 = {
              title: 'Top 10 Produkte',
              legend: 'none',
              pieSliceText: 'label',
              slices: {  4: {offset: 0.2},
                        12: {offset: 0.3},
                        14: {offset: 0.4},
                        15: {offset: 0.5},
              },
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));
            chart.draw(data2, options2);
          
  //#######################################################################################################################
  
            var data_char3 = new google.visualization.DataTable();
            data_char3.addColumn("string", "Topping");    
            data_char3.addColumn("number", "Slices");
            data_char3.addRows([
             [tmp[22],parseInt(tmp[23])],
             [tmp[24],parseInt(tmp[25])],
             [tmp[26],parseInt(tmp[27])],

        ]);
            
            var options_char3 = {"title":"TOP 3 Maerkte",
                    "width":400,
                    "height":300};
            
            var chart3 = new google.visualization.PieChart(document.getElementById("chart_div3"));
            chart3.draw(data_char3, options_char3);

//#######################################################################################################################
             var data_char4 = new google.visualization.DataTable();
            data_char4.addColumn('number', 'X');
            data_char4.addColumn('number', 'Euro');

            data_char4.addRows([
              [1, parseInt(tmp[28])], 
              [2, parseInt(tmp[29])], 
              [3, parseInt(tmp[30])], 
              [4, parseInt(tmp[31])],
              [5, parseInt(tmp[32])],
              [6, parseInt(tmp[33])],
              [7, parseInt(tmp[34])],
              [8, parseInt(tmp[35])],
              [9, parseInt(tmp[36])],
              [10, parseInt(tmp[37])],
              [11, parseInt(tmp[38])],
              [12, parseInt(tmp[39])],
            ]);

            var options_char4 = {
              hAxis: {
                title: 'Monat'
              },
              vAxis: {
                title: 'Ausgaben'
              }
            };

            var chart4 = new google.visualization.LineChart(document.getElementById('chart_div4'));
            chart4.draw(data_char4, options_char4);
      
      

 //#######################################################################################################################
   
  var data_char5 = new google.visualization.DataTable();
        data_char5.addColumn("string", "Info");
        data_char5.addColumn("string", "Name");
        data_char5.addColumn("string", "Value");

        data_char5.addRows([
          [tmp[40], tmp[41],tmp[42]],
          [tmp[43], tmp[44],tmp[45]],
          [tmp[46], tmp[47],tmp[48]],
          [tmp[49], tmp[50],tmp[51]],
          [tmp[52], tmp[53],tmp[54]],
          [tmp[55], tmp[56],tmp[57]],
          [tmp[58], tmp[59],tmp[60]],
          [tmp[61], tmp[62],tmp[63]],
          [tmp[64], tmp[65],tmp[66]]


        ]);
          
            var char5 = new google.visualization.Table(document.getElementById("chart_div5"));
            char5.draw(data_char5, {showRowNumber: true, width: "100%", height: "100%"});
 
 
  //#######################################################################################################################
}

    </script>
  </head>
  <body>
    <div id="chart_div" style="width: 900px; height: 500px;"></div>
    <div id="chart_div2" style="width: 900px; height: 500px;"></div>
        <div id="chart_div3" style="width: 900px; height: 500px;"></div>
            <div id="chart_div4" style="width: 900px; height: 500px;"></div>
                <div id="chart_div5" style="width: 900px; height: 500px;"></div>
    
</body>
</html>