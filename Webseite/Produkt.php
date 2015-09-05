<?php
echo "<h2>Produkt</h2>";

echo "<tr>" ;
echo "<th> ID</th>";
echo "<th> Name</th>";
echo "<th> Gewicht</th>";
echo "<th> Preis</th>";
echo "</tr>";

class Produkt
{
	private $mstrName;
	private $mintGewicht;
	private $mfltPreis;
	private $mintID;
	
	function Produkt($pstrName, $pstrGewicht, $pfltPreis, $pintID)
	{
		$this->mstrName = $pstrName;
		$this->mintGewicht = $pstrGewicht;
		$this->mfltPreis = $pfltPreis;
		$this->mintID = $pintID;
	}
	
	
	function ausgabe()
	{
	
		echo "<tr>" ;
		echo "<td> .$this->mintID,. ID</td>";
		echo "<td> .$this->mstrName. Name</td>";
		echo "<td> .$this->mintGewicht. Gewicht </td>";
		echo "<td> .$this->mfltPreis. Preis</td>";
		echo "</tr>";
	}
	
	function eintragen()
	{
		return "insert into Produkt(name,gewicht,preis) Values('$this->mstrName',$this->mintGewicht,$this->mfltPreis);";
	}
	
	function update()
	{
		return "update Produkt set name = '$this->mstrName' , gewicht = $this->mintGewicht, preis = $this->mfltPreis where P_ID = $this->mintID;";
		
	}
	
}

?>