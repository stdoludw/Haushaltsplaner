<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

        
<head>
<script type="text/javascript" src="https://www.google.com/jsapi">
</script>

        <script type="text/javascript">
         google.load("visualization", "1.0", {"packages":["table"]});
          google.setOnLoadCallback(drawChart);
          
          function drawChart() {
            var data_char1 = new google.visualization.DataTable();
            data_char1.addColumn("string", "Topping");    
            data_char1.addColumn("number", "Slices");
            data_char1.addRows([
              ["Jan - Maer", ' 1 '],
              ["Apr - Jun", '2'],
              ["Jul - Sep", ' 4'],
              ["Okt - Dez", '5'],
        ]);
        
           
 
   var options_char1 = {"title":"Ausgaben pro Quartal",
                           "width":400,
                           "height":300};
                           
  
       
            var chart = new google.visualization.PieChart(document.getElementById("chart_div"));
            chart.draw(data_char1, options_char1);
            
   
          }
        </script>
</head>






 <body>
        <div id="chart_div"></div>
    

    </body>
</html>