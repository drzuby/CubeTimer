#include <iostream>
#include <iomanip>
using namespace std;

string move[106]; //œcianka w scramblu
string movex[106]; //œcianka + warstwy w scramblu - do zabezpieczeñ
string scramble[106]; //ruch w scramblu
string turns[6] = {"R", "U", "F", "L", "D", "B"}; //mo¿liwe œcianki
int pos[6] = {1, 2, 3, 1, 2, 3}; //do zabezpiecze¿ñ typu F B F
int poss[106]; //do zabezpieczeñ typu F B F
string lay[3] = {"", "w", "3"}; //mo¿liwe postfixy
int moves, posts, layers; //zmienne do losowania œcianek, postfixów i warstw
float czas; //czas u³o¿enia
float suma = 0; //suma czasów u³o¿eñ do liczenia œrednich
int size; //rozmiar koœci
int length; //d³ugoœæ scrambla
string back; //zwracanie przez funkcje 'genarator'

string generator_two(int i)
{
       do
       {
         moves = rand()%3;
         move[i] = turns[moves];
       } while (move[i] == move[i - 1]);
       
       scramble[i] = move[i];
       
       posts = rand()%3;
       switch (posts)
       {
              case 0: break;
              case 1: scramble[i] += "'";
                   break;
              case 2: scramble[i] += "2";
                   break;
       }
       
       return scramble[i];
}

string generator_three(int i)
{
       do
       {
         moves = rand()%6;
         move[i] = turns[moves];
         poss[i] = pos[moves];
       } while (move[i] == move[i - 1] ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2])
               );
       
       scramble[i] = move[i];
       
       posts = rand()%3;
       switch (posts)
       {
              case 0: break;
              case 1: scramble[i] += "'";
                   break;
              case 2: scramble[i] += "2";
                   break;
       }
       
       return scramble[i];
}

string generator_four_five(int i)
{
       do
       {
         moves = rand()%6;
         layers = rand()%2;
         move[i] = movex[i] = turns[moves];
         movex[i] += lay[layers];
         poss[i] = pos[moves];
       } while (movex[i] == movex[i - 1] ||
               (move[i - 1] == move[i - 2] && move[i] == move[i - 1]) ||
               (poss[i] == poss[i - 1] && movex[i] == movex[i - 2]) ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2] && movex[i] == movex[i - 3]) ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2] && poss[i] == poss[i - 3] && poss[i] == poss[i - 4])
               );
       
       scramble[i] = movex[i];
       
       posts = rand()%3;
       switch (posts)
       {
              case 0: break;
              case 1: scramble[i] += "'";
                   break;
              case 2: scramble[i] += "2";
                   break;
       }
       
       return scramble[i];
}

string generator_six_seven(int i)
{
       do
       {
         moves = rand()%6;
         layers = rand()%3;
         move[i] = movex[i] = turns[moves];
         if (lay[layers] == "3")
            movex[i] = lay[layers] + movex[i];
            else
                movex[i] += lay[layers];
         poss[i] = pos[moves];
       } while (movex[i] == movex[i - 1] ||
               (move[i] == move[i - 1] && move[i] == move[i - 2] && move[i] == move[i - 3]) ||
               (poss[i] == poss[i - 1] && movex[i] == movex[i - 2]) ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2] && movex[i] == movex[i - 3]) ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2] && poss[i] == poss[i - 3] && movex[i] == movex[i - 4]) ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2] && poss[i] == poss[i - 3] && poss[i] == poss[i - 4] && movex[i] == movex[i - 5]) ||
               (poss[i] == poss[i - 1] && poss[i] == poss[i - 2] && poss[i] == poss[i - 3] && poss[i] == poss[i - 4] && poss[i] == poss[i - 5] && poss[i] == poss[i - 6])
               );
       
       scramble[i] = movex[i];
       
       posts = rand()%3;
       switch (posts)
       {
              case 0: break;
              case 1: scramble[i] += "'";
                   break;
              case 2: scramble[i] += "2";
                   break;
       }
       
       return scramble[i];
}

string generator(int size, int i)
{
       switch (size)
       {
              case 2: back = generator_two(i);
                   break;
              case 3: back = generator_three(i);
                   break;
              case 4: back = generator_four_five(i);
                   break;
              case 5: back = generator_four_five(i);
                   break;
              case 6: back = generator_six_seven(i);
                   break;
              case 7: back = generator_six_seven(i);
                   break;
       }
       
       return back;
}

int main()
{   
    srand(time(NULL));
    move[0] = move [1] = move[2] = move[3] = move[4] = move[5] = "";
    movex[0] = movex[1] = movex[2] = movex[3] = movex[4] = movex[5] = "";
    poss[0] = poss[1] = poss[2] = poss[3] = poss[4] = poss[5] = 0;
    cout.setf(ios::fixed);
    
    cout << "SCRAMBLER" << endl;
    cout << "_________" << endl;
    cout << "Aby wyjsc z programu jako czas ulozenia podaj wartosc '0'" << endl;
    cout << endl;
    cout << "Wybierz rozmiar kosci (2 - 7): ";
    cin >> size;
    
    switch (size)
    {
           case 2: length = 15;
                break;
           case 3: length = 30;
                break;
           case 4: length = 45;
                break;
           case 5: length = 65;
                break;
           case 6: length = 85;
                break;
           case 7: length = 105;
                break;
    }
    
    for (int i = 1; ; ++i)
    {
        cout << "Algorytm mieszajacy:" << endl;
    
        for (int j = 6; j <= length; ++j)
            cout << generator(size, j) << " ";
        
        cout << endl;
        cout << "Podaj swoj czas: ";
        cin >> czas;
        if (czas == 0)
           break;
           else
           {
               suma += czas;
               cout << "Twoja srednia z " << i << " czasow wynosi " << setprecision(2) << suma/i << " s.\n\n\n" << endl;
           }
    }
    
    cout << endl;
    cout << "Dziekujemy i zapraszamy ponownie :)" << endl;
    
    cin.ignore();
    getchar();
    return 0;
}
