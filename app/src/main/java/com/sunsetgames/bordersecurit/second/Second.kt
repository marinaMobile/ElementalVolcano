package com.sunsetgames.bordersecurit.second

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.sunsetgames.bordersecurit.ApplCla
import com.sunsetgames.bordersecurit.ApplCla.Companion.BALANCE_VOLCANOS
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.ActivitySecondBinding
import kotlin.random.Random

class Second : AppCompatActivity() {
    private lateinit var kadsmasdk : ActivitySecondBinding
    private var countBet = 5
    private var isPressed = false
    private lateinit var sharedPreference : SharedPreferences
    private lateinit var totalSharedPref : SharedPreferences
    var totalBalance: Int? = 0
    private var winRes = 0
    private var fisrtImgTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kadsmasdk = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(kadsmasdk.root)
        totalSharedPref = getSharedPreferences("VOLCANO_BAL_SP", MODE_PRIVATE)
        totalBalance = totalSharedPref.getInt(ApplCla.BALANCE_VOLCANOS.toString(), 0)
        sharedPreference = getSharedPreferences("win_res", Context.MODE_PRIVATE)
        val def_txt_win = sharedPreference.getInt("win_res",winRes)
        kadsmasdk.tvSecWin.text = "Your win : $def_txt_win"
        kadsmasdk.tvSecBlnce.text = "Your balance : $totalBalance"
        kadsmasdk.lgOutSec.setOnClickListener {
            startActivity(Intent(this,StartSecScr::class.java))
        }
        getBet()
        if(isPressed == false){
            kadsmasdk.spBut.setOnClickListener {
                    if(totalBalance!= 0){
                        if(countBet<=totalBalance!!){
                            totalBalance = totalBalance!!.minus(countBet)
                            kadsmasdk.tvSecBlnce.text = "Your balance : $totalBalance"
                            totalSharedPref.edit().putInt(ApplCla.BALANCE_VOLCANOS.toString(), totalBalance!!).apply()

                            if(totalBalance!!<0){
                                totalBalance = 0
                                kadsmasdk.tvSecBlnce.text = "Your balance : $totalBalance"
                                totalSharedPref.edit().putInt(ApplCla.BALANCE_VOLCANOS.toString(), totalBalance!!).apply()
                            }
                            firstLaucnhImg()
                        } else {
                               Toast.makeText(this, "Your balance is lower thant bet", Toast.LENGTH_SHORT).show()
                              countBet = 5
                              kadsmasdk.tvBet.text = countBet.toString()
                        }

                    } else {
                        AlertSecGm.showdialog(this)
                    }
            }

            }
        }





    private fun getBet() = with(kadsmasdk){
        plusSec.setOnClickListener {
            if(totalBalance!! > countBet){
                countBet +=5
                tvBet.text = countBet.toString()
                if(countBet >= 200){
                    countBet = 5
                    tvBet.text = countBet.toString()
                }
            } else {
                countBet = totalBalance!!
                tvBet.text = countBet.toString()
            }
        }

    }

    private fun calcwin() = with(kadsmasdk){
        val rndom = SecGmeUtils.bonus[Random.nextInt(12)]
        val getOld = sharedPreference.getInt("win_res",0)
        val total = getOld + countBet * rndom
        sharedPreference.edit().putInt("win_res",total.toInt()).apply()
        tvSecWin.text = "Your win : ${total.toInt()}"
    }


    private fun firstLaucnhImg() = with(kadsmasdk){
        fisrtImgTimer?.cancel()
        txtPrs.visibility = View.GONE
        img1.rotation = 0.0f
        img2.rotation = 0.0f
        img3.rotation = 0.0f
        img4.rotation = 0.0f
        img5.rotation = 0.0f
        img6.rotation = 0.0f

        lgImg.visibility = View.GONE
        gridSec.visibility = View.VISIBLE
        img1.visibility = View.VISIBLE
        var suaidasjdsajksdka = Random.nextInt(20) + 10
        suaidasjdsajksdka = suaidasjdsajksdka * 36
        fisrtImgTimer = object : CountDownTimer((suaidasjdsajksdka * 20).toLong(), 1) {
            override fun onTick(millisUntilFinished: Long) {
                isPressed = true
                spBut.isClickable = false
                spBut.alpha = 0.7f
                plusSec.isClickable = false
                val losapdpsapsda = img1.rotation + 2
                img1.rotation = losapdpsapsda

                 img2.visibility = View.VISIBLE
                    val rot2 = img2.rotation + 2
                    img2.rotation = rot2



                    img3.visibility = View.VISIBLE
                    val rot3 = img3.rotation + 2
                    img3.rotation = rot3



                    img4.visibility = View.VISIBLE
                    val rot4 = img4.rotation + 2
                    img4.rotation = rot4


                    img5.visibility = View.VISIBLE
                    val rot5 = img5.rotation + 2
                    img5.rotation = rot5


                    img6.visibility = View.VISIBLE
                    val rot6 = img6.rotation + 2
                    img6.rotation = rot6

            }

            override fun onFinish() {
                isPressed = false
                spBut.alpha = 1.0f
                spBut.isClickable = true
                plusSec.isClickable = true

                gridSec.visibility = View.GONE
                img1.visibility = View.GONE
                img2.visibility = View.GONE
                img3.visibility = View.GONE
                img4.visibility = View.GONE
                img5.visibility = View.GONE
                img6.visibility = View.GONE
                txtPrs.visibility = View.VISIBLE
                lgImg.visibility = View.VISIBLE
                calcwin()
            }

        }.start()
    }



    override fun onDestroy() {
        super.onDestroy()
        fisrtImgTimer?.cancel()
    }

}