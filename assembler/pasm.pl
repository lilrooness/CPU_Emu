#!/usr/bin/perl
if(scalar(@ARGV)<2){
	die("Usage: <INPUT> <OUTPUT>\n");
}

#load assmebly file
open(FILE, $ARGV[0]);
@commands = <FILE>;
my @opcodes;

for$command(@commands){
	#pass MOV commands
	if($command =~m/^MOV.*/i){
		push(@opcodes, mov($command));
	#pass ADD commands
	}elsif($command=~m/^ADD.*/i){
		push(@opcodes, add($command));
	}elsif($command=~m/^SUB.*/i){
		push(@opcodes, subt($command));
	}elsif($command=~m/^PUSH.*/i){
		push(@opcodes, stackPush($command));
	}elsif($command=~m/^POP.*/i){
		push(@opcodes, stackPop($command));
	}else{
		print("Error in line $l");
		die();
	}
}

#save the assembled opcodes to file specified
open(OUTPUT, '>',$ARGV[1]);
print OUTPUT join("\n",@opcodes);
close(OUTPUT);

#compiles a MOV command
sub mov{
	$l=$_[0];
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
	}elsif($l=~ /.*PC, ([0-9A-F]{2})$/i){
		$l=~/([0-9A-F]{2})$/i;
		return "09".$1;
	}elsif($l=~ /.*A, PC$/i){
		return "1400";
	}elsif($l=~ /.*B, PC$/i){
		return "1500";
	}else{
		print("Error in line $l");
		die();
	}
}

#compiles an ADD command
sub add{
	$l=$_[0];
	if($l=~/.*A, B$/i){
		return "0C00";
	}elsif($l=~/.*B, A$/i){
		return "0D00";	
	}elsif($l=~/.*A, ([0-9A-B]{2})$/i){
		$l=~/([0-9A-F]{2})$/i;
		return "0E".$1;
	}elsif($l=~/.*B, ([0-9A-B]{2})$/i){
		$l=~/([0-9A-F]{2})$/i;
		return "0F".$1;
	}else{
		print("Error in line $l");
		die();
	}
}

#compiles SUB command
sub subt{
	$l=$_[0];
	if($l=~/.*A, B$/i){
		return "1000";
	}elsif($l=~/.*B, A$/i){
		return "1100";
	}elsif($l=~/.*A, ([0-9A-F]{2})$/i){
		$l=~/([0-9A-F]{2})$/i;
		return "12".$1;
	}elsif($l=~/.*B, ([0-9A-F]{2})$/i){
		$l=~/([0-9A-F]{2})$/i;
		return "13".$1;
	}else{
		print("Error in line $l");
		die();
	}
}

#compiles PUSH command
sub stackPush{
	$l=$_[0];
	if($l=~/.* A$/i){
		return "1700";
	}elsif($l=~/.* B$/i){
		return "1800";
	}elsif($l=~/.* PC$/i){
		return "1600";
	}else{
		print("Error in line $l");
		die();
	}
}

#compiles POP command
sub stackPop{
	$l=$_[0];
	if($l=~/.* A/i){
		return "1A00";
	}elsif($l=~/.* B/i){
		return "1B00";
	}elsif($l=~/.* PC/i){
		return "1900";
	}else{
		print("Error in line $l");
		die();
	}
}