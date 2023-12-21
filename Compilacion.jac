program calificaciones;
int count,cali,par;
float total,suma,promedio,proto;
char materia,m;
{
materia='a';
while(m!='n'){
	print("Ingrese la materia (Matematicas, Español, Quimica) o x para cerrar el programa");
	materia=read();
	if(materia!='x')then{
		count=0;
		cali=0;
		suma=0;
		par=0;
		print("Ingrese cuantos parciales seran calificados\n");
		par=read();
		while(count<par){
				print("Ingrese la calificacion: ",count, "\n");
				cali=read();
				suma=suma+cali;
				count=count+1;
		}
		promedio=suma/par;
		if(promedio<70)then{
		print("el promedio no es aprobatorio:",promedio, "\n");
		}else
		{
		print("el promedio es aprobatorio:",promedio, "\n");
		}
	}else
		{
			print("Buen día :D\n");
			m='n';
		}
	
}
}