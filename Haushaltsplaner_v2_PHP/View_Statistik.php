<html>
    <head>

        <?php
        echo '<script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
         google.load("visualization", "1.0", {"packages":["corechart", "bar", "table"]});
          google.setOnLoadCallback(drawChart);
          
          function drawChart() {
            var data_char1 = new google.visualization.DataTable();
            data_char1.addColumn("string", "Topping");    
            data_char1.addColumn("number", "Slices");
            data_char1.addRows([
              ["Jan - Maer", ' . $kuchen[0] . '],
              ["Apr - Jun", ' . $kuchen[1] . '],
              ["Jul - Sep", ' . $kuchen[2] . '],
              ["Okt - Dez", ' . $kuchen[3] . '],

        ]);
        
            var data_char2 = new google.visualization.DataTable();
            data_char2.addColumn("string", "Topping");    
            data_char2.addColumn("number", "Slices");
            data_char2.addRows([
             ["' . $kuchenProduktName[0] . '",' . $kuchenProduktValue[0] . '],
              ["' . $kuchenProduktName[1] . '",' . $kuchenProduktValue[1] . '],
              ["' . $kuchenProduktName[2] . '",' . $kuchenProduktValue[2] . '],
              ["' . $kuchenProduktName[3] . '",' . $kuchenProduktValue[3] . '],
              ["' . $kuchenProduktName[4] . '",' . $kuchenProduktValue[4] . '],
              ["' . $kuchenProduktName[5] . '",' . $kuchenProduktValue[5] . '],
              ["' . $kuchenProduktName[6] . '",' . $kuchenProduktValue[6] . '],
              ["' . $kuchenProduktName[7] . '",' . $kuchenProduktValue[7] . '],
              ["' . $kuchenProduktName[8] . '",' . $kuchenProduktValue[8] . '],
              ["' . $kuchenProduktName[9] . '",' . $kuchenProduktValue[9] . '],
]);

 var data_char3 = new google.visualization.DataTable();
            data_char3.addColumn("string", "Topping");    
            data_char3.addColumn("number", "Slices");
            data_char3.addRows([
             ["' . $kuchenMarktName[0] . '",' . $kuchenMarktValue[0] . '],
              ["' . $kuchenMarktName[1] . '",' . $kuchenMarktValue[1] . '],
              ["' . $kuchenMarktName[2] . '",' . $kuchenMarktValue[2] . '],
        ]);
        
          
 var data_char4 = google.visualization.arrayToDataTable([
        ["Monat", "Totale Ausgaben",],
        ["Januar", ' . $bar[0] . '],
        ["Februar",' . $bar[1] . '],
        ["März", ' . $bar[2] . '],
        ["April", ' . $bar[3] . '],
        ["Mai", ' . $bar[4] . '],
        ["Juni", ' . $bar[5] . '],
        ["Juli", ' . $bar[6] . '],
        ["August", ' . $bar[7] . '],
        ["September", ' . $bar[8] . '],
        ["Oktober", ' . $bar[9] . '],
        ["November", ' . $bar[10] . '],
        ["Dezember", ' . $bar[11] . ']
      ]);
    
     var data_char5 = new google.visualization.DataTable();
        data_char5.addColumn("string", "Name");
        data_char5.addColumn("string", "Value");
        data_char5.addColumn("string", "Value2");
        data_char5.addColumn("string", "Info");

        data_char5.addRows([
          ["'. $tableStatistikName[0].'","'. $tableStatistikValue[0].'","'. $tableStatistikValue2[0].'","'. $tableStatistikInfo[0]. '"],
          ["'. $tableStatistikName[1].'","'. $tableStatistikValue[1].'","'. $tableStatistikValue2[1].'","'. $tableStatistikInfo[1]. '"],
          ["'. $tableStatistikName[2].'","'. $tableStatistikValue[2].'","'. $tableStatistikValue2[2].'","'. $tableStatistikInfo[2]. '"],
          ["'. $tableStatistikName[3].'","'. $tableStatistikValue[3].'","'. $tableStatistikValue2[3].'","'. $tableStatistikInfo[3]. '"],
          ["'. $tableStatistikName[4].'","'. $tableStatistikValue[4].'","'. $tableStatistikValue2[4].'","'. $tableStatistikInfo[4]. '"],
          ["'. $tableStatistikName[5].'","'. $tableStatistikValue[5].'","'. $tableStatistikValue2[5].'","'. $tableStatistikInfo[5]. '"],
          ["'. $tableStatistikName[6].'","'. $tableStatistikValue[6].'","'. $tableStatistikValue2[6].'","'. $tableStatistikInfo[6]. '"],
          ["'. $tableStatistikName[7].'","'. $tableStatistikValue[7].'","'. $tableStatistikValue2[7].'","'. $tableStatistikInfo[7]. '"],
          ["'. $tableStatistikName[8].'","'. $tableStatistikValue[8].'","'. $tableStatistikValue2[8].'","'. $tableStatistikInfo[8]. '"],
          ["'. $tableStatistikName[9].'","'. $tableStatistikValue[9].'","'. $tableStatistikValue2[9].'","'. $tableStatistikInfo[9]. '"]

        ]);
 
   var options_char1 = {"title":"Ausgaben pro Quartal",
                           "width":400,
                           "height":300};
                           
  var options_char2 = {"title":"TOP 10 Produkten",
                           "width":400,
                           "height":300};
                           
 var options_char3 = {"title":"TOP 3 Maerkte",
                           "width":400,
                           "height":300};
                           
                           
   var options_char4 = {
        chartArea: {width: "50%"},
        hAxis: {
          minValue: 0
        },
        vAxis: {
          title: "City"
        }
      };

       
            var chart = new google.visualization.PieChart(document.getElementById("chart_div"));
            chart.draw(data_char1, options_char1);
            
            var chart2 = new google.visualization.PieChart(document.getElementById("chart_div2"));
            chart2.draw(data_char2, options_char2);
            
            var chart3 = new google.visualization.PieChart(document.getElementById("chart_div3"));
            chart3.draw(data_char3, options_char3);
            
             var chart4 = new google.visualization.BarChart(document.getElementById("chart_div4"));
            chart4.draw(data_char4, options_char4);
            
            var char5 = new google.visualization.Table(document.getElementById("chart_div5"));
            char5.draw(data_char5, {showRowNumber: true, width: "100%", height: "100%"});
          }
        </script>
       '
        ?>

    </head>

    <body>
        <div id="chart_div"></div>
        <div id="chart_div2"></div>
        <div id="chart_div3"></div>
        <div id="chart_div4"></div>
        <div id="chart_div5"></div>

    </body>
</html>