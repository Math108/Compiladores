import java.util.Scanner;
public class meuPrograma{ 
   public static void main(String args[]){ 
   Scanner _scTrx = new Scanner(System.in);
    int a;
    int b;
    double c;
    double w;
	String x;
    int i;
    double y;
    int j;
    double k;
System.out.println("Hello World");
System.out.println("Fim do programa");
c = -1.1;
c = 1+2;
k = 200*2+120*3;
w = c+k;
w = 200*3/2;
a = _scTrx.nextInt();
b = _scTrx.nextInt();
System.out.println(a);
i = 0;
j = 0;
while (i<9||j<9) {i = i+1;
System.out.println("estou no while");
}if (a>5) {System.out.println("maior que 5");
}else {System.out.println("menor que 5");
}do {j = j+1;
System.out.println("estou no doWhile");
}while ((!(j>=9)));while (i<10&&j<10) {j = j+1;
i = i+1;
System.out.println("teste and");
}System.out.println("oi agora vamos testar outro");
if (b>=0) {System.out.println("b positivo");
}else {System.out.println("b negativo");
}   }
}
