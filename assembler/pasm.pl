#!/usr/bin/perl
if(scalar(@ARGV)<1){
	die("Usage: <FILENAME>\n");
}

#load assmebly file
open(FILE, $ARGV[0]) or die("File not found <".$ARGV[0].">\n");
#load opcodes file
open(OPCODES, "opcodes") or die("Cannot find opcodes file\n");

@commands = <FILE>;
@available = <OPCODES>;
my @opcodes;

for$command(@commands){
	#pass mov commands
	if($command =~m/^MOV.*/i){
		push(@opcodes, mov($command));
	}elsif($command=~m/^ADD.*/i){
		
	}
}

print join("\n",@opcodes);
print "\n";

sub mov{
	$l=$_[0];
	print "$l";
	if($l=~/.*A, B$/i){
		return "0300";
	}elsif($l=~/.*B, A$/i){
		return "0400";
	}elsif($l=~/.A, \$/i){
		$l =~ m/\$([0-9A-F]+)/i;
		return "05".$1;
	}elsif($l=~/.*B, \$/i){
		$l =~ m/\$([0-9A-F]+)/i;
		return "05".$1;
	}elsif($l=~/.*\$([0-9A-F]{2}), A/i){
		$l =~ m/\$([0-9A-F]{2})/i;
		return "0A".$1;
	}elsif($l=~/.*\$([0-9A-F]{2}), B/i){
		$l =~ m/\$([0-9A-F]{2})/i;
		return "0B".$1;
	}elsif($l=~/.*A, ([0-9A-F]{2})$/i){
		$l =~ m/([0-9A-F]{2})$/i;
		return "01".$1;
	}elsif($l=~/.*B, ([0-9A-F]{2})$/i){
		$l =~ m/([0-9A-F]{2})$/i;
		return "02".$1;
	}elsif($l=~/.*PC, A$/i){
		return "0700";
	}elsif($l=~/.*PC, B$/i){
		return "0800";
	}elsif($l=~ /PC, ([0-9A-F]{2})$/i){
		$l=~/([0-9A-F]{2})$/i;
		return "09".$1;
	}else{
		print("Error in line $l");
		die();
	}
}