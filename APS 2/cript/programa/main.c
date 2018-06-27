#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// Configuracao da chave para criptografia
const char CHAVE[]="AbCd";
int TAM_CHAVE=strlen(CHAVE);
//----------------------------------------

//Vetor de entrada do texto a ser criptografado
char TEXTO[100000];
int TAM_TEXTO=100000;
//---------------------------------------------

int TEXTO_CRIPT[100000];

bool Criptografar(){
    int i=0;
    int CHAVE_int = 0;
    int aux=0;

    for(i=0; i< strlen(TEXTO); i++){
        TEXTO_CRIPT[i]=TEXTO[i];
    }
    //printf("\n -- %d -- \n",TEXTO_CRIPT[1]);// Imprime parte do texto apenas para conferência.
    for (aux=0; aux < strlen(TEXTO) ;){
        for(i=0;i<TAM_CHAVE; i++){
            // TEXTO_CRIPT[aux] = TEXTO_CRIPT[aux] * CHAVE[i]
            CHAVE_int = CHAVE[i];
            TEXTO_CRIPT[aux] *= CHAVE_int;
            aux++;
        }
    }
   // printf("\n -- %d -- \n",TEXTO_CRIPT[1]);// Imprime parte do texto apenas para conferência.

    return true;
}

bool Descriptografar(){
    FILE *arquivo;

    int valor;
    int pos_chave;
    int pos_texto;

    arquivo = fopen("F:\\cript\\arquivos_recebidos\\criptografado.txt","r");

    if (arquivo==NULL){
        return false;
    }

    pos_chave = 0;
    pos_texto=0;

    while(!feof(arquivo)){
        fscanf(arquivo,"%d",&valor);
        valor /= CHAVE[pos_chave];
        TEXTO[pos_texto] =(char)valor;
        pos_texto++;
        if(pos_chave==TAM_CHAVE-1){
            pos_chave=0;
        }else{
            pos_chave++;
        }
    }
    fclose(arquivo);

    return true;
}

void Recebe_texto(char tipo[]){
    char aux[TAM_TEXTO];
    char pause;
    bool ok;

    FILE *arquivo;
    char palavra[100];
    if(strcmp(tipo,"msg")==0){
        printf("Receber texto para criptografar");
        printf("\n\nDigite o texto: ");
        gets(aux);
        fflush(stdin);

        if(strlen(aux) <= TAM_TEXTO){
            strcpy(TEXTO,aux);
            printf("\n\nCriptografando texto...\n\n");
            ok=true;
        }else{
            printf("\n\nErro!\nTamanho do texto execede o limite de caracteres\n\n");
            ok=false;
            system("pause");
        }
    }else if(strcmp(tipo,"arquivo")==0){
        printf("\n\nIntrucoes para importar um arquivo de texto: ");
        printf("\n\n 1 - Renomeie o arquivo para 'original.txt");
        printf("\n\n 2 - Copie o arquivo para a pasta que sera aberta a seguir");
        printf("\n\n 3 - Feche o explorador e retorne a aplicacao");

        printf("\n\n\nPrecione Enter para abrir a pasta...\n");
        scanf("%c",&pause);
        setbuf(stdin,NULL);
        system("explorer F:\\cript\\arquivos_originais\\");
        printf("\n\nPressione o Enter apos colar e renomear o arquivo...");
        scanf("%c",&pause);
        setbuf(stdin,NULL);
        printf("\n\nImportando arquivo...\n\n");

        arquivo = fopen("F:\\cript\\arquivos_originais\\original.txt","r");
        if (arquivo==NULL){
            printf("\n\nErro ao abrir o arquivo...\n\n");
        }else{
            while(!feof(arquivo)){
                fscanf(arquivo,"%s",palavra);
                strcat(aux," ");
                strcat(aux,palavra);
            }
            fclose(arquivo);
            if(strlen(aux) <= TAM_TEXTO){
                strcpy(TEXTO,aux);
                printf("\n\nCriptografando texto...\n\n");
                ok=true;
            }else{
                printf("\n\nErro!\nTamanho do texto execede o limite de caracteres\n\n");
                ok=false;
                system("pause");
            }
        }
    }else{
        printf("Erro ao receber texto\n\n");
    }
    if(ok){
        if (Criptografar()){
            int i=0;
            printf("\n\nTexto criptografado com sucesso!");
            arquivo = fopen("eF\\cript\\arquivos_gerados\\criptografado.txt","wt");
            if (arquivo==NULL){
                printf("\n\nErro ao criar o arquivo...\n\n");
            }else{
                for(i=0; i < strlen(TEXTO); i++){
                    fprintf(arquivo,"%d ",TEXTO_CRIPT[i]);
                }
            }
            fclose(arquivo);
            printf("\n\nPressione enter tecla para abrir a pasta do arquivo gerado.");
            scanf("%c",&pause);
            setbuf(stdin,NULL);
            system("explorer F:\\cript\\arquivos_gerados\\");
        }else{
            printf("\n\nNao foi possivel criptografar o texto!");
        }
    }
}

void Receber_Arquivo_cript(){
    printf("\n\nIntrucoes para importar um arquivo criptografado: ");
    printf("\n\n 1 - Renomeie o arquivo para 'criptografado.txt");
    printf("\n\n 2 - Copie o arquivo para a pasta que sera aberta a seguir");
    printf("\n\n 3 - Feche o explorador e retorne a aplicacao");

    printf("\n\n\nPrecione Enter para abrir a pasta\n\n");
    system("pause");
    system("explorer e:\\cript\\arquivos_recebidos\\");
    printf("\n\nPressione o Enter apos colar o arquivo na pasta...\n\n");
    system("pause");
    printf("\n\nImportando arquivo...\n\n");

    if (Descriptografar()){
        printf("\nArquivo descriptografado com sucesso!\n\n");
        system("pause");
        system("cls");
        printf("\n%s\n\n",TEXTO);
        system("pause");
   }else{
        printf("\n Nao foi possivel descriptografar o arquivo!\n\n");
        system("pause");
   }
}

void Menu_Criptografar(){
    int opcao;
    printf("\n\tCriptografia de dados");
    printf("\n\n Criptografar texto ou arquivo");
    printf("\n\n 1 - Digitar texto");
    printf("\n\n 2 - Importar arquivo de texto (txt)");
    printf("\n\n 3 - Voltar ao menu principal");
    printf("\n\n Informe a opcao desejada: ");
    scanf("%d",&opcao);
    setbuf(stdin,NULL);
    switch(opcao){
    case 1:
        system("cls");
        Recebe_texto("msg");
        break;
    case 2:
        system("cls");
        Recebe_texto("arquivo");
        break;
    case 3:
        break;
    default:
        printf("Opcao invalida! Tente novamente...");
    }
}

void Menu(){
    int opcao;
    while(1){
        system("cls");
        printf("\n\tCriptografia de dados");
        printf("\n\n 1 - Criptografar texto");
        printf("\n\n 2 - Receber arquivo");
        printf("\n\n 3 - Sair");
        printf("\n\n Informe a opcao desejada: ");
        scanf("%d",&opcao);
        setbuf(stdin,NULL);
        switch(opcao){
        case 1:
            system("cls");
            Menu_Criptografar();
            break;
        case 2:
            system("cls");
            Receber_Arquivo_cript();
            break;
        case 3:
            printf("\nFinalizando...\n\n");
            system("pause");
            exit(0);
            break;
        default:
            printf("Opcao invalida! Tente novamente...");
        }
    }
}

int main(){
    Menu();
    return 0;
}
