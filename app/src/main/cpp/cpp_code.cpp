//
// Created by Leo on 26/04/2023.
//

#include <jni.h>
#include <cstring>
#include <iostream>
#include <cmath>
#include <list>
#include <iterator>
#include <cstdlib>

using namespace std;

extern "C" {
// {Leo, 26APR} - Retrieves the name of the game for the main screen

JNIEXPORT jstring JNICALL
Java_com_example_snakescreenprototype_MainMenu_getText(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("Serpent");
}

// {Leo, 26APR} - Computes, displays, or sets the score based on string input
JNIEXPORT jint JNICALL
Java_com_example_snakescreenprototype_MainGameScreen_Score(JNIEnv *env, jobject thiz,
                                                           jstring jString) {
    const char *cString = env->GetStringUTFChars(jString, 0);
    static float score = 0;
    if (strcmp(cString, "Start") == 0) {
        score = 0;
    } else if (strcmp(cString, "AddTime") == 0) {
        // {Leo, 26APR} - Adds a point for every 5 movements you complete
        score += 0.2;
    } else if (strcmp(cString, "AddApple") == 0) {
        score += 10;
    } else if (strcmp(cString, "Show") == 0) {
        score = score;
    }

    return 5 * ((int) score);
}

// {Leo, 27APR} - Added function for highscore:
JNIEXPORT jstring JNICALL
Java_com_example_snakescreenprototype_MainMenu_highScore(JNIEnv *env, jobject thiz, jint hsa) {
    static list<int> highScore = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    highScore.push_back(hsa);
    highScore.sort();
    highScore.reverse();
    highScore.pop_back();
    int ii = 0;
    char scoreOutput[100];
    for (std::list<int>::iterator jj = highScore.begin(); jj != highScore.end(); jj++) {
        if (not(ii)) {
            sprintf(scoreOutput, "HIGH SCORES\n%d. %d\n", ii + 1, *jj);
        } else {
            char temp[10];
            sprintf(temp, "%d. %d\n", ii + 1, *jj);
            strcat(scoreOutput, temp);
        }
        ii++;
    }
    return env->NewStringUTF(scoreOutput);
}

JNIEXPORT jint JNICALL
Java_com_example_snakescreenprototype_MainGameScreen_doubleit(JNIEnv *env, jobject thiz, jint num) {
    int c_num = (int) num;
    return (jint) 2 * c_num;
}

JNIEXPORT jint JNICALL
Java_com_example_snakescreenprototype_MainGameScreen_leftclick(JNIEnv *env, jobject thiz,
                                                               jint mvps) {
    int c_mvps = (int) mvps;
    c_mvps--;
    if (c_mvps == -1) {
        c_mvps = 3;
    }
    return (jint) c_mvps;
}

JNIEXPORT jint JNICALL
Java_com_example_snakescreenprototype_MainGameScreen_rightClick(JNIEnv *env, jobject thiz,
                                                                jint mvps) {
    int c_mvps = (int) mvps;
    c_mvps++;
    if (c_mvps == 4) {
        c_mvps = 0;
    }
    return (jint) c_mvps;
}

JNIEXPORT jint JNICALL
Java_com_example_snakescreenprototype_MainGameScreen_setX(JNIEnv *env, jobject thiz,
                                                          jint surfaceWidth, jint pointsize) {
    int c_pointsize = (int) pointsize;

    srand((unsigned) time(NULL));
    int randomX = rand() % ((int) surfaceWidth / c_pointsize);

    // check if randomXPosition is even or odd. We only need even numbers
    if ((randomX % 2) != 0) {
        randomX++;
    }

    return (jint) (c_pointsize * randomX) + c_pointsize;
}

JNIEXPORT jint JNICALL
Java_com_example_snakescreenprototype_MainGameScreen_setY(JNIEnv *env, jobject thiz,
                                                          jint surfaceHeight, jint pointsize) {
    int c_pointsize = (int) pointsize;

    srand((unsigned) time(NULL));
    int randomY = rand() % ((int) surfaceHeight / c_pointsize);

    // check if randomXPosition is even or odd. We only need even numbers
    if ((randomY % 2) != 0) {
        randomY++;
    }

    return (jint) (c_pointsize * randomY) + c_pointsize;
}

}
