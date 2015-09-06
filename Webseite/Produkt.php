<?php
class Produkt {
	private $mstrName;
	private $mintGewicht;
	private $mfltPreis;
	private $mintID;
	function Produkt($pstrName, $pstrGewicht, $pfltPreis, $pintID) {
		$this->mstrName = $pstrName;
		$this->mintGewicht = $pstrGewicht;
		$this->mfltPreis = $pfltPreis;
		$this->mintID = $pintID;
	}
	function ausgabe() {
		echo "<td> $this->mintP_ID</td>";
		echo "<td> $this->mstrP_Name</td>";
		echo "<td> $this->mintGewicht</td>";
		echo "<td> $this->mfltPreis</td>";
	}
	function eintragen() {
		return "insert into Produkt(name,gewicht,preis) Values('$this->mstrName',$this->mintGewicht,$this->mfltPreis);";
	}
	function update() {
		return "update Produkt set name = '$this->mstrName' , gewicht = $this->mintGewicht, preis = $this->mfltPreis where P_ID = $this->mintID;";
	}
}

?>