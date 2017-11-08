// This is a mutant program.
// Author : ysma

package com.lyq.mutant.AOIU_13;


import java.io.IOException;


public class ChinaUnionBill
{

    double talkTimePer = 0.15;

    int freeTalkTime = 0;

    double freeFlow = 0;

    int voiceCall = 0;

    double flow = 0;

    public  double billCaculation( char type, int monthRent, double flow, int voiceCall, int videoCall )
        throws java.io.IOException
    {
        this.flow = flow;
        this.voiceCall = voiceCall;
        switch (type) {
        case 'a' :
        case 'A' :
            switch (monthRent) {
            case 46 :
                talkTimePer = 0.25;
                pretreatment( voiceCall, flow, 50, 150 );
                break;

            case 66 :
                talkTimePer = 0.2;
                pretreatment( voiceCall, flow, 50, 300 );
                break;

            case 96 :
                pretreatment( voiceCall, flow, 240, 300 );
                break;

            case 126 :
                pretreatment( voiceCall, flow, 320, 400 );
                break;

            case 156 :
                pretreatment( -voiceCall, flow, 420, 500 );
                break;

            case 186 :
                pretreatment( voiceCall, flow, 510, 650 );
                break;

            case 226 :
                pretreatment( voiceCall, flow, 700, 750 );
                break;

            case 286 :
                pretreatment( voiceCall, flow, 900, 950 );
                break;

            case 386 :
                pretreatment( voiceCall, flow, 1250, 1300 );
                break;

            case 586 :
                pretreatment( voiceCall, flow, 1950, 2000 );
                break;

            case 886 :
                pretreatment( voiceCall, flow, 3000, 3000 );
                break;

            default  :
                throw new java.io.IOException( "Invalid monthRent!" );

            }
            break;

        case 'b' :
        case 'B' :
            switch (monthRent) {
            case 46 :
                talkTimePer = 0.25;
                pretreatment( voiceCall, flow, 120, 40 );
                break;

            case 66 :
                talkTimePer = 0.2;
                pretreatment( voiceCall, flow, 200, 60 );
                break;

            case 96 :
                pretreatment( voiceCall, flow, 450, 80 );
                break;

            case 126 :
                pretreatment( voiceCall, flow, 680, 100 );
                break;

            case 156 :
                pretreatment( voiceCall, flow, 920, 120 );
                break;

            case 186 :
                pretreatment( voiceCall, flow, 1180, 150 );
                break;

            default  :
                throw new java.io.IOException( "Invalid monthRent!" );

            }
            break;

        case 'c' :
        case 'C' :
            switch (monthRent) {
            case 46 :
                talkTimePer = 0.2;
                pretreatment( voiceCall, flow, 260, 40 );
                break;

            case 66 :
                talkTimePer = 0.2;
                pretreatment( voiceCall, flow, 380, 60 );
                break;

            case 96 :
                talkTimePer = 0.15;
                pretreatment( voiceCall, flow, 550, 80 );
                break;

            default  :
                throw new java.io.IOException( "Invalid monthRent!" );

            }
            break;

        default  :
            throw new java.io.IOException( "Invalid monthRent!" );

        }
        return monthRent + 0.6 * videoCall + talkTimePer * (this.voiceCall - freeTalkTime) + 0.3 * (this.flow - freeFlow);
    }

    private  void pretreatment( int voiceCall, double flow, int freeTalkTime, double freeFlow )
    {
        this.freeTalkTime = freeTalkTime;
        this.freeFlow = freeFlow;
        if (voiceCall <= freeTalkTime) {
            this.voiceCall = freeTalkTime;
        }
        if (flow <= freeFlow) {
            this.flow = freeFlow;
        }
    }

}
