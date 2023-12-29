#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "dictionary.h"

void destroyDictionary(Dictionary *d) {
    free(d->words);
    free(d->definitions);
    for(int i = 0; i < d->count; i++) {
        free(d->words[i]);
        free(d->definitions[i]);
    }
    free(d);
}

Dictionary* createDictionary(int startCap) {
    Dictionary* d = (Dictionary*)malloc(sizeof(Dictionary*));
    d->words = (char**)malloc(sizeof(char*)*startCap);
    d->definitions = (char**)malloc(sizeof(char*)*startCap);
    for(int i = 0; i < startCap; i++) {
        d->words[i] = (char*)malloc(sizeof(char) * (MAX_WORD_LENGTH + 1));
        d->definitions[i] = (char*)malloc(sizeof(char) * (MAX_DEFINITION_LENGTH + 1));
    }
    d->capacity = startCap;
    d->count = 0;
    return d;
}

void addWord(Dictionary *d, char* word, char* def) {
    int index = d->count;
    strcpy(d->words[index], word);
    strcpy(d->definitions[index], def);
    d->count++;
}

char* getWord(Dictionary *d, char* word) {
    for(int i = 0; i < d->count; i++) {
        if(strcmp(d->words[i], word) == 0) {
            return d->definitions[i];
        }
    }
    return "COULD NOT FIND WORD";
}
