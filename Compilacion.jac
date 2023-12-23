program factor;
int numero,conta,fac;
{
fac=1;
conta=1;
numero=-1;
while(numero<0){
print("Ingrese un numero para calcular su factorial:");
numero=read();
if(numero<0)then{
	print("el factorial no esta definido para numeros negativos\n");
}else{
	while(conta<=numero){
		fac=conta*fac;
		conta=conta+1;
	}
	print("el factorial de ",numero);
	print(" es ",fac);
}
}
}