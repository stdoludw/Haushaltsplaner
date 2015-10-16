<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="jsp.access"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="w3.css">
<body w3-white-grey>

<nav class="w3-sidenav w3-white w3-card-2 w3-animate-left" style="display:none">
  <a href="javascript:void(0)"
  onclick="w3_close()"
	    class="w3-closenav w3-large">Close</a>
      <a href="index.html">Login</a>
  <a href="login-success.jsp">Hauptmenue</a>
  <a href="usermanagement.jsp"> Benutzerverwaltung </a>
  
</nav>

<header class="w3-container w3-blue-grey">
  <span class="w3-opennav w3-large" onclick="w3_open()">&#9776; Menue </span>
</header>

<div class="w3-row-padding w3-center w3-margin-top">


<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">


//########################################
	google.load("visualization", "1.1", {
		"packages" : [ "corechart", "bar", "table", "line" ]
	});
	google.setOnLoadCallback(drawChart);
	
//########################################
<%ArrayList<String> tmp = access.getStatistik();%>
//########################################

	function drawChart() {
		
//########################################
		var data = google.visualization.arrayToDataTable([
				[ 'Monat', 'Ausgaben' ],
				[ 'Januar - Maerz',
<%out.print(tmp.get(0));%>
	],
				[ 'April - Juni',
<%out.print(tmp.get(1));%>
	],
				[ 'Juli - September',
<%out.print(tmp.get(2));%>
	],
				[ 'Oktober - Dezember',
<%out.print(tmp.get(3));%>
	], ]);

		var options = {
			title : 'Ausgaben pro Quartal',
			is3D : true,
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);

//########################################

		var data2 = google.visualization.arrayToDataTable([
				[ 'Produktname', 'Anzahl' ], 
				[' <%out.print(tmp.get(4));%>', <%out.print(tmp.get(5));%> ],
				[ '<%out.print(tmp.get(6));%>', <%out.print(tmp.get(7));%> ], 
				[ '<%out.print(tmp.get(8));%>',<%out.print(tmp.get(9));%> ],
		[ '<%out.print(tmp.get(10));%>',<%out.print(tmp.get(11));%> ],
		[ '<%out.print(tmp.get(12));%>',<%out.print(tmp.get(13));%> ],
		[ '<%out.print(tmp.get(14));%>',<%out.print(tmp.get(15));%> ],
		[ '<%out.print(tmp.get(16));%>',<%out.print(tmp.get(17));%> ],
		[ '<%out.print(tmp.get(18));%>',<%out.print(tmp.get(19));%> ],
		[ '<%out.print(tmp.get(20));%>',<%out.print(tmp.get(21));%> ],
		[ '<%out.print(tmp.get(22));%>',<%out.print(tmp.get(23));%> ]]);

		var options2 = {
			title : 'Top 10 Produkte',
			legend : 'none',
			is3D : true,
			pieSliceText : 'label',
			slices : {
				4 : {
					offset : 0.2
				},
				12 : {
					offset : 0.3
				},
				14 : {
					offset : 0.4
				},
				15 : {
					offset : 0.5
				},
			},
		};

		var chart2 = new google.visualization.PieChart(document
				.getElementById('chart_div2'));
		chart2.draw(data2, options2);
		
//########################################

		 var data_char3 = new google.visualization.arrayToDataTable([
		[ 'Name', 'Ausgaben' ],
          ['<%out.print(tmp.get(24));%>',<%out.print(tmp.get(25));%>],
          ['<%out.print(tmp.get(26));%>',<%out.print(tmp.get(27));%>],
          ['<%out.print(tmp.get(28));%>',<%out.print(tmp.get(29));%>],

     ]);
         
         var options_char3 = {"title":"TOP 3 Maerkte"
                 };
         
         var chart3 = new google.visualization.PieChart(document.getElementById("chart_div3"));
         chart3.draw(data_char3, options_char3);
//########################################

         var data_char4 = new google.visualization.DataTable();
         data_char4.addColumn('number', 'X');
         data_char4.addColumn('number', 'Euro');

         data_char4.addRows([
           [1, <%out.print(tmp.get(30));%>], 
           [2, <%out.print(tmp.get(31));%>], 
           [3, <%out.print(tmp.get(32));%>], 
           [4, <%out.print(tmp.get(33));%>],
           [5, <%out.print(tmp.get(34));%>],
           [6, <%out.print(tmp.get(35));%>],
           [7, <%out.print(tmp.get(36));%>],
           [8, <%out.print(tmp.get(37));%>],
           [9, <%out.print(tmp.get(38));%>],
           [10, <%out.print(tmp.get(39));%>],
           [11, <%out.print(tmp.get(40));%>],
           [12, <%out.print(tmp.get(41));%>],
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
//########################################

         var data_char5 = new google.visualization.DataTable();
         data_char5.addColumn("string", "Info");
         data_char5.addColumn("string", "Name");
         data_char5.addColumn("string", "Value");
         data_char5.addColumn("string", "Value");

         data_char5.addRows([
           ["AVG(entfernung)",'<%out.print(tmp.get(42));%>', '<%out.print(tmp.get(43));%>','<%out.print(tmp.get(44));%>'],
           ["MIN(entfernung)",'<%out.print(tmp.get(45));%>', '<%out.print(tmp.get(46));%>','<%out.print(tmp.get(47));%>'],
           ["MAX(entfernung)",'<%out.print(tmp.get(48));%>', '<%out.print(tmp.get(49));%>','<%out.print(tmp.get(50));%>'],
           ["MAX(anzahl)",'<%out.print(tmp.get(51));%>', '<%out.print(tmp.get(52));%>','<%out.print(tmp.get(53));%>'],
           ["MIN(anzahl)",'<%out.print(tmp.get(54));%>', '<%out.print(tmp.get(55));%>','<%out.print(tmp.get(56));%>'],
           ["MAX(datum)",'<%out.print(tmp.get(57));%>', '<%out.print(tmp.get(58));%>','<%out.print(tmp.get(59));%>'],
           ["MIN(datum)",'<%out.print(tmp.get(60));%>', '<%out.print(tmp.get(61));%>','<%out.print(tmp.get(62));%>'],
           ["MAX(preis)", '<%out.print(tmp.get(63));%>', '<%out.print(tmp.get(64));%>','<%out.print(tmp.get(65));%>'],
           ["MIN(preis)",'<%out.print(tmp.get(66));%>', '<%out.print(tmp.get(67));%>','<%out.print(tmp.get(68));%>'],
           ["AVG(preis)",'<%out.print(tmp.get(69));%>', '<%out.print(tmp.get(70));%>','<%out.print(tmp.get(71));%>']

         ]);
           
             var char5 = new google.visualization.Table(document.getElementById("chart_div5"));
             char5.draw(data_char5, {showRowNumber: true, width: "100%", height: "100%"});
  
       


	}
</script>

<script>
function w3_open() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
}
function w3_close() {
    document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
}
</script>

</head>
<body>


	<div class="w3-row-padding"> 
<div class="w3-third">
<div class="w3-card-2">
	<div id="chart_div" style="width: 700px; height: 500px;"></div>
	 <div class="w3-container">
  </div>
</div>
</div>

	
	<div class="w3-row-padding"> 
<div class="w3-third">
<div class="w3-card-2">	
	<div id="chart_div2" style="width: 900px; height: 500px;"></div>
		 <div class="w3-container">
  </div>
</div>
</div>

<div class="w3-row-padding"> 
<div class="w3-third">
<div class="w3-card-2">
<div id="chart_div3" style="width: 700px; height: 500px;"></div>
<div class="w3-container">
			 
  </div>
</div>
</div>

		<div class="w3-row-padding w3-center  w3-margin-top"> 
<div class="w3-half">
<div class="w3-card-2">
<div id="chart_div4" style="width: 1200px; height: 500px;"></div>
			 <div class="w3-container">
  </div>
</div>
</div>
	
	
	<center>
<div id="chart_div5" style="width: 900px; height: 500px;"></div>
</center>


</body>
</html>