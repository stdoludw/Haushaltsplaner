<?php
class Konto {
	private $mstrBetrag;
	private $mstrName;
	private $mstrBLZ;
	private $mstrKnr;
	private $mstrMin;
	private $mintID;
	
	function Konto($pstrName, $pstrBetrag, $pstrBLZ, $pstrKnr, $pstrMin, $pintID, $pstrPasswort) {
		
		// ausführen von aes
		$this->mstrBetrag = exec("java aes bro $mstrBetrag Encode");
		$this->mstrName = $mstrName;
		$this->mstrBLZ = $mstrBLZ;
		$this->mstrKnr = $mstrKnr;
		$this->mstrMin = $mstrMin;
		$this->mintID = $mintID;
		
		
	}
	
	function ausgabe() {
	}
}
