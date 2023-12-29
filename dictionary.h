#define MAX_WORD_LENGTH 20
#define MAX_DEFINITION_LENGTH 50

struct Dictionary {
    char** words;
    char** definitions;
    int capacity;
    int count;
};

typedef struct Dictionary Dictionary;

void destroyDictionary(Dictionary *d);
Dictionary* createDictionary(int startCap);
void addWord(Dictionary *d, char* word, char* def);
char* getWord(Dictionary *d, char* word);
int strEquals(char* one, char* two);
