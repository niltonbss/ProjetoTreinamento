#include <stdio.h>
#include <math.h>

int main(){
 
    int vetor[3], qnt_prod, cnj_prod=0, j;
    printf("Digite o Numero de produtos\n"); 
    scanf("%d", &qnt_prod);

    for(j=0; j<qnt_prod; j++)
    {
        printf("Digite as dimensoes do produto\n");
        int i;
        for(i=0; i<3; i++)
        {
            scanf("%d", &vetor[i]);
            
        }

        int tam_prod = (vetor[0] * vetor[1] * vetor[2]);

        cnj_prod = tam_prod + cnj_prod;
    }
    float dimensoes = pow (cnj_prod,(1.0/3.0));
    printf("A dimensao ideal eh %f X %f X %f\n", dimensoes, dimensoes, dimensoes);
    int cx1 = 96000, cx2 = 160000, cx3 = 240000, num_cx;
    if (cnj_prod <= cx1)
    {
        num_cx = 1;
    }
    else if (cnj_prod <= cx2)
    {
        num_cx = 2;
    }
    else if (cnj_prod <= cx3)
    {
        num_cx = 3;
    }
    else 
    {
        num_cx = -1;
    }
    printf("A caixa para acomodar os produtos eh Caixa %d\n", num_cx);
    return 0;
}