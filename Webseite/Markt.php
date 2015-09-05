<?php

class Markt
{
	private  $mstrName;
	private  $mstrPLZ;
	private  $mstrAdr;
	private  $mintEntfernung;
	private  $mintID;
	
	function Markt($pstrName, $pstrPlz, $pstrAdr, $pstrEntfernung, $pintID)
	{
	
		$this->mstrName = $pstrName;
		$this->mstrPLZ = $pstrPlz;
		$this->mstrAdr = $pstrAdr;
		$this->mintEntfernung = $pstrEntfernung;
		$this->mintID = $pintID;
		
		
	}
	
	function ausgabe()
	{
		echo "test";
	}

}





?>