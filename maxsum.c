#include <unistd.h>
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <limits.h>
#include <stdbool.h>
#include <pthread.h>

// Print out an error message and exit.
static void fail( char const *message ) {
  fprintf( stderr, "%s\n", message );
  exit( 1 );
}

// Print out a usage message, then exit.
static void usage() {
  printf( "usage: maxsum <workers>\n" );
  printf( "       maxsum <workers> report\n" );
  exit( 1 );
}

// Input sequence of values.
int *vList;

// Number of values on the list.
int vCount = 0;

// Capacity of the list of values.
int vCap = 0;

//max of the entire list.
int max = INT_MIN;

// Read the list of values.
void readList() {
  // Set up initial list and capacity.
  vCap = 5;
  vList = (int *) malloc( vCap * sizeof( int ) );

  // Keep reading as many values as we can.
  int v;
  while ( scanf( "%d", &v ) == 1 ) {
    // Grow the list if needed.
    if ( vCount >= vCap ) {
      vCap *= 2;
      vList = (int *) realloc( vList, vCap * sizeof( int ) );
    }

    // Store the latest value in the next array slot.
    vList[ vCount++ ] = v;
    //printf("Tester: val = %d\n", v);
  }
}

void* threadFunc(void *arg) {
    //the argument this thread is given is an int array with several key values
    int *arr = (int*)arg;
    
    //the maximum of this thread
    int localMax = INT_MIN; 
    
    //represents what starting indexes this thread is responsible for 
    int i = arr[0];
    
    //represents the number of workers- this determines how much the index
    //increases by each time
    int workers = arr[1];
    
    //represents whether or not we will report the sum of this thread
    int report = arr[3];
    
    //records the sum of all the values so far
    int sum = 0;
    
    //go through all the starting indexes I'm responsible for
    for(int n = i; n < vCount; n += workers) {
        sum = 0; //will be added to at every traversed index
        
        //go through the possible ending indexes for this starting index
        //add to the sum along the way
        for(int k = n; k < vCount; k++) {
            sum += vList[k];
            //for each added value, compare the new sum
            //to the previous highest known sum
            if(sum > localMax) {
                localMax = sum;
            }
        }
    }
    
    //if we're asked to report our sum, do so.
    if(report) {
        printf("I'm thread %d. The maximum sum I found is %d.\n", (int)pthread_self(), localMax);
    }
    arr[2] = localMax;
    
    return NULL;
}



int main( int argc, char *argv[] ) {
  bool report = false;
  int workers;

  // Parse command-line arguments.
  if ( argc < 2 || argc > 3 )
    usage();

  if ( sscanf( argv[ 1 ], "%d", &workers ) != 1 ||
       workers < 1 )
    usage();

  // If there's a second argument, it better be the word, report
  if ( argc == 3 ) {
    if ( strcmp( argv[ 2 ], "report" ) != 0 )
      usage();
    report = true;
  }

  readList();
  
  for(int i = 0; i < workers; i++) { //each worker will have one thread 
    pthread_t threadVal; //the thread value to be used later
    int threadArgs[4]; //an array that will contain useful values for the thread
    threadArgs[0] = i; //store which indexes the thread will be responsible for
    threadArgs[1] = workers; //store how many indexes to skip each time
    
    //this variable will be changed by the thread- by reading it,
    //we will know what its local maximum was
    threadArgs[2] = INT_MIN; 
    
    //store the report variable as an int
    if(report) {
        threadArgs[3] = 1;
    } else {
        threadArgs[3] = 0;
    }
    
    //create the thread, failing if it cannot make it.
    //we pass in the threadArgs array so it may operate on it
    if ( pthread_create( &threadVal, NULL, threadFunc, threadArgs ) != 0 ) 
        fail( "Can't create a child thread" );
    
    // Wait for our child thread to exit (it will probably run a little
    // bit longer than me)
    pthread_join( threadVal, NULL );
    
    //threadArgs[2] stores the local maxmimum of the thread
    //if this local maximum is greater than our maximum, it's the new maximum
    if(threadArgs[2] > max) {
        max = threadArgs[2];
    }
  }
  //print the final sum of the program
  printf("Maximum Sum: %d\n", max);
}