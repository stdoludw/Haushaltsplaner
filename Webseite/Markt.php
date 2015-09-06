<?php


class Markt {
	private $mstrName;
	private $mstrPLZ;
	private $mstrAdr;
	private $mintEntfernung;
	private $mintID;
	
	function Markt($pstrName, $pstrPlz, $pstrAdr, $pstrEntfernung, $pintID) {
		$this->mstrName = $pstrName;
		$this->mstrPLZ = $pstrPlz;
		$this->mstrAdr = $pstrAdr;
		$this->mintEntfernung = $pstrEntfernung;
		$this->mintID = $pintID;
	}
	
	function ausgabe() {
			 return array($this->mstrName, $this->mstrPLZ, $this->mstrAdr, $this->mintEntfernung, $this->mintID);
		
	}
	
	function eintragen() {
		return "insert into Markt (name,postleitzahl,adresse,entfernung) Values('$this->mstrName', '$this->mstrPLZ', '$this->mstrAdr', $this->mintEntfernung);";
	}
	function update() {
		return "update Markt set name = '$this->mstrName' , postleitzahl = '$this->mstrPLZ' ,
		adresse = $this->mstrAdr' , entfernung = $this->mintEntfernung where M_ID = $this->mintID ;";
	}
}

?>