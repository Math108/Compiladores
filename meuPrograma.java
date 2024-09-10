import java.util.Scanner;
public class meuPrograma{ 
   public static void main(String args[]){ 
   Scanner _scTrx = new Scanner(System.in);
    int a;
    int b;
    double c;
	String x;
    int i;
	String y;
    int j;
System.out.println("Hello World");
System.out.println("Fim do programa");
c = 1.1;
a = _scTrx.nextInt();
b = _scTrx.nextInt();
System.out.println(a);
i = 0;
j = 0;
while (i<9) {i = i+1;
System.out.println("estou no while");
}if (a>5) {System.out.println("maior que 5");
}else {System.out.println("menor que 5");
}do {j = j+1;
System.out.println("estou no doWhile");
}while (j<9);System.out.println("oi agora vamos testar outro");
if (b>=0) {System.out.println("b positivo");
}else {System.out.println("b negativo");
}   }
}
