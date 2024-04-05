import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String nomeFuncionario;
        String[] nomeMaiorMenor = {null, null};
        char continuar;
        int horas, i;
        int maxFuncionario = 15;
        int[] cont100 = {0};
        float salario, media = 0;
        float[] salarioMaiorMenor = {Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY}; //Os valores passados como parâmetro no vetor, servem para que qualquer valor fornecido pelo usuário seja capturado e inserido nas primeiras posições do vetor

        for(i = 0; i < maxFuncionario; i++){
            nomeFuncionario = receberNome(i);
            horas = receberHoras(nomeFuncionario);
            salario = calcularSalario(horas, cont100);
            avaliarMenorMaior(nomeFuncionario,salario,salarioMaiorMenor,nomeMaiorMenor);
            media += salario;

            System.out.println("\nNome do " + (i +1) + "° funcionário: "+ nomeFuncionario);
            System.out.println("Salário do funcionário " + nomeFuncionario + " R$: " + salario);
            continuar = verificarFim();
            if(continuar == 'N'){
                break;
            }
        }

        System.out.println("\nMédia geral de salário dos funcionários R$: " + String.format("%.2f",(media/(i+1))));
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Número de funcionários que trabalharam 100 horas ou mais: " + cont100[0]);
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Nome do funcionário com o maior salário: " + nomeMaiorMenor[0]);
        System.out.println("Salário do funcionário: " + salarioMaiorMenor[0]);
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Nome do funcionário com o menor salário: " + nomeMaiorMenor[1]);
        System.out.println("Salário do funcionário: " + salarioMaiorMenor[1]);
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Finalizando...");

        sc.close();

    }

    //Método responsável por capturar o nome fornecido pelo usuário e retornar na variável "nome"
    public static String receberNome(int numero) {
        Scanner sc = new Scanner(System.in);
        String nome;

        do {
            System.out.print("\nInforme o nome do " + (numero + 1) + "° Funcionário: ");
            nome = sc.nextLine();
        }while (nome.isEmpty()); //Validação para que o usuário não forneça um valor nulo

        return nome;

    }

    //Método responsável por receber e validar a quantidade de horas trabalhadas pelo usuário e retorna o valor na variável "horasTrabalhadas"
    public static int receberHoras(String nomeFuncionario){
        Scanner sc = new Scanner(System.in);
        int horasTrabalhadas;

        do {
            System.out.print("Informe a quantidade de horas trabalhadas pelo funcionário " + nomeFuncionario + ": ");
            horasTrabalhadas = sc.nextInt();

            if(horasTrabalhadas < 0){
                System.out.println("Erro: O número de horas trabalhadas não pode ser negativo.\n");
            } else if(horasTrabalhadas > 160){
                System.out.println("Erro: O número de horas trabalhadas não pode exceder 160 horas mensais.\n");
            }

        } while (horasTrabalhadas < 0 || horasTrabalhadas > 160); //Validando se o número de horas é maior do que 0 e menor do que 160

        return horasTrabalhadas;
    }

    //Método responsável por verificar se o usuário deseja continuar com o cadastro de funcionários
    public static char verificarFim(){
        Scanner sc = new Scanner(System.in);
        char entrada;

        do{
            System.out.print("\nDeseja inserir mais funcionários? (S-Sim ou N-Não):");
            entrada = sc.next().toUpperCase().charAt(0);

            if((entrada != 'S') && (entrada != 'N')){
                System.out.println("Erro: Entrada inválida. Tente novamente.\n");
            }

        } while (entrada != 'N' && entrada != 'S');
        return entrada;
    }

    //Método responsável pelo cálculo do salário com base nas horas trabalhadas e seguindo as regras de remuneração. O retorno é feito pela variável "salario"
    public static float calcularSalario(int horas, int[] cont100){
        float salario = 0;
        if(horas >= 100){
            cont100[0]++;
        }
        if (horas <= 50) {
            salario += (float) (horas * 12.50);
        } else if (horas <= 80) {
            salario += (float) (horas * 17.90);
        } else if (horas <= 120){
            salario += (float) (horas * 21.70);
        } else {
            salario += (float) (horas * 25.40);
            if(horas > 150) {
                salario+= (float) (salario * 0.15);
            }
        }

        return salario;
    }

    //Método responsável por validar e armazenar no vetor o nome e salário dos funcionários de maior e menor salários
    public static void avaliarMenorMaior(String nome, float salario, float[] salarioMaiorMenor, String[] nomeMaiorMenor){
        if (salario > salarioMaiorMenor[0]) {
            salarioMaiorMenor[0] = salario;
            nomeMaiorMenor[0] = nome;
        }

        if (salario < salarioMaiorMenor[1]) {
            salarioMaiorMenor[1] = salario;
            nomeMaiorMenor[1] = nome;
        }

    }

}