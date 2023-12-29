#include <stdio.h>
#include <stdlib.h>

#include "dictionary.h"
/*
Goal: implement a basic hashmap in C

This should include a resizing function if the
load factor is greater than a value
-store as much in the Dictionary as possible

Stretch Goal: transform Dictionary to use generic type
-perhaps have size_t variable for the size of the type
-maybe have a large number of methods to work with

Stretch Goal: use header file and Makefile to store
important information/functions
*/


int main()
{
    FILE* fp = stdin;

    printf("Enter the maximum capacity of the dictionary:\n");

    int cap;
    fscanf(fp, "%d", &cap);

    Dictionary *d = createDictionary(cap);

    int response;
    char word[MAX_WORD_LENGTH];
    char define[MAX_DEFINITION_LENGTH];
    while(1) {
        printf("Select an option: \n1: Enter new word\n2: Retrieve word\n3: Quit\n");

        fscanf(fp, "%d", &response);

        if(response == 1) {
            if(d->count == d->capacity) {
                printf("Dictionary full. May not add more words.\n");
                continue;
            }
            printf("Enter new word:\n");
            fscanf(fp, "%s", word);

            printf("Enter its definition:\n");
            fscanf(fp, "%s", define);


            addWord(d, word, define);
        } else if(response == 2) {
            printf("Enter word:\n");
            fscanf(fp, "%s", word);

            printf("Definition: %s\n", getWord(d, word));
        } else if(response == 3) {
            destroyDictionary(d);
            break;
        } else {
            printf("Invalid option.\n");
        }
        printf("\n");
    }
    return 0;
}
