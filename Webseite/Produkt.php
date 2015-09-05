<?php

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
	
	function aausgabe()
	{
		
	}
	
}

?>