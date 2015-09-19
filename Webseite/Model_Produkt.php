<?php
class Model_Produkt {
	private $mstrName;
	private $mintGewicht;
	private $mfltPreis;
	private $mintID;
        
	function __construct($pstrName, $pstrGewicht, $pfltPreis, $pintID) {
		$this->mstrName = $pstrName;
		$this->mintGewicht = $pstrGewicht;
		$this->mfltPreis = $pfltPreis;
		$this->mintID = $pintID;
	}
	function array_return() {
		
	return array($this->mintID, $this->mstrName, $this->mintGewicht,$this->mfltPreis);
		
	}
	function eintragen() {
		return "insert into Produkt(name,gewicht,preis) Values('$this->mstrName',$this->mintGewicht,$this->mfltPreis);";
	}
	function update() {
		return "update Produkt set name = '$this->mstrName' , gewicht = $this->mintGewicht, preis = $this->mfltPreis where P_ID = $this->mintID;";
	}
}
