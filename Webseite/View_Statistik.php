<html>
      <head>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
          google.load('visualization', '1.0', {'packages':['corechart', 'bar']});
          google.setOnLoadCallback(drawChart);
          function drawChart() {
            var data_char1 = new google.visualization.DataTable();
            data_char1.addColumn('string', 'Topping');    
            data_char1.addColumn('number', 'Slices');
            data_char1.addRows([
              ['Mushrooms', 3],
              ['Onions', 1],
              ['Olives', 1],
              ['Zucchini', 1],
              ['Pepperoni', 2]
            ]);
            var data_char2 = new google.visualization.DataTable();
            data_char2.addColumn('string', 'Year');
            data_char2.addColumn('number', 'Sales');
            data_char2.addColumn('number', 'Expenses');
            data_char2.addRows([
              ['2004', 1000, 400],
              ['2005', 1170, 460],
              ['2006',  860, 580],
              ['2007', 1030, 540]
            ]);
          var data_char3 = new google.visualization.DataTable();
      data_char3.addColumn('timeofday', 'Time of Day');
      data_char3.addColumn('number', 'Motivation Level');
      data_char3.addRows([
        [{v: [8, 0, 0], f: '8 am'}, 1],
        [{v: [9, 0, 0], f: '9 am'}, 2],
        [{v: [10, 0, 0], f:'10 am'}, 3],
        [{v: [11, 0, 0], f: '11 am'}, 4],
        [{v: [12, 0, 0], f: '12 pm'}, 5],
        [{v: [13, 0, 0], f: '1 pm'}, 6],
        [{v: [14, 0, 0], f: '2 pm'}, 7],
        [{v: [15, 0, 0], f: '3 pm'}, 8],
        [{v: [16, 0, 0], f: '4 pm'}, 9],
        [{v: [17, 0, 0], f: '5 pm'}, 10],
      ]);
 
 var data_char4 = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2013',  1000,      400],
          ['2014',  1170,      460],
          ['2015',  660,       1120],
          ['2016',  1030,      540]
        ]);
       var options_char1 = {'title':'How Much Pizza I Ate Last Night',
                           'width':400,
                           'height':300};
      var options_char2 = {'title':'Line chart',
                          'width':400,
                           'height':300};
         var options_char3 = {
        title: 'Motivation Level Throughout the Day',
        hAxis: {
          title: 'Time of Day',
          format: 'h:mm a',
          viewWindow: {
            min: [7, 30, 0],
            max: [17, 30, 0]
          }
        },
        vAxis: {
          title: 'Rating (scale of 1-10)'
        }
      };
       var options_char4 = {
          title: 'Company Performance',
          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data_char1, options_char1);
            var chart2 = new google.visualization.LineChart(document.getElementById('chart_div2'));
            chart2.draw(data_char2, options_char2);
                  var char3 = new google.charts.Bar(document.getElementById('chart_div3'));
      char3.draw(data_char3, options_char3);
  var char4 = new google.visualization.AreaChart(document.getElementById('chart_div4'));
      char4.draw(data_char4, options_char4);
          }
        </script>
      </head>

      <body>
        <div id="chart_div"></div>
        <div id="chart_div2"></div>
        <div id="chart_div3"></div>
        <div id="chart_div4"></div>
      </body>
    </html>