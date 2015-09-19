<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Model_Markt
 *
 * @author dominik
 */

class Model_Markt {
	var $mstrName;
	var $mstrPLZ;
	var $mstrAdr;
	var $mintEntfernung;
	var $mintID;
	
	function __construct($pstrName, $pstrPlz, $pstrAdr, $pstrEntfernung, $pintID) {
		$this->mstrName = $pstrName;
		$this->mstrPLZ = $pstrPlz;
		$this->mstrAdr = $pstrAdr;
		$this->mintEntfernung = $pstrEntfernung;
		$this->mintID = $pintID;
	}
	
     
	function array_return() {
		
		return array($this->mintID, $this->mstrName,
		 $this->mstrPLZ, $this->mstrAdr, $this->mintEntfernung);		
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
