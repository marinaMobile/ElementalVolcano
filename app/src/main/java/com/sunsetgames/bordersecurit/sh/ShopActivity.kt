package com.sunsetgames.bordersecurit.sh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.QonversionError
import com.qonversion.android.sdk.QonversionOfferingsCallback
import com.qonversion.android.sdk.QonversionPermissionsCallback
import com.qonversion.android.sdk.dto.QPermission
import com.qonversion.android.sdk.dto.offerings.QOfferings
import com.qonversion.android.sdk.dto.products.QProduct
import com.sunsetgames.bordersecurit.ApplCla
import com.sunsetgames.bordersecurit.R
import com.sunsetgames.bordersecurit.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewProductsList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProductsList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        Qonversion.offerings(object : QonversionOfferingsCallback {
            override fun onSuccess(offerings: QOfferings) {
                val mainProducts = offerings.main?.products
                mainProducts?.let {
                    binding.recyclerViewProductsList.adapter = ShopAdapter(it) { product ->
                        purchase(product)
                    }

                } ?:  Toast.makeText(this@ShopActivity, "There are no products in main offering", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: QonversionError) {
                Toast.makeText(this@ShopActivity, error.description, Toast.LENGTH_LONG).show()

            }
        })
    }


    private fun purchase(product: QProduct) {
        Qonversion.purchase(this, product, callback = object :
            QonversionPermissionsCallback {
            override fun onSuccess(entitlements: Map<String, QPermission>) {
                Toast.makeText(this@ShopActivity, "Purchase succeeded", Toast.LENGTH_LONG).show()
                if (product.qonversionID == "clover_bundle_small") {
                    val totalBalanceSP = getSharedPreferences("VOLCANO_BAL_SP", MODE_PRIVATE)
                    val totalB = totalBalanceSP.getInt(ApplCla.BALANCE_VOLCANOS.toString(), 0)
                    totalBalanceSP.edit().putInt(ApplCla.BALANCE_VOLCANOS.toString(), totalB + 499)
                        .apply()
                }
                if (product.qonversionID == "clover_bundle_medium") {
                    val totalBalanceSP = getSharedPreferences("VOLCANO_BAL_SP", MODE_PRIVATE)
                    val totalB = totalBalanceSP.getInt(ApplCla.BALANCE_VOLCANOS.toString(), 0)
                    totalBalanceSP.edit().putInt(ApplCla.BALANCE_VOLCANOS.toString(), totalB + 2999)
                        .apply()
                }
                if (product.qonversionID == "clover_bundle_big") {
                    val totalBalanceSP = getSharedPreferences("VOLCANO_BAL_SP", MODE_PRIVATE)
                    val totalB = totalBalanceSP.getInt(ApplCla.BALANCE_VOLCANOS.toString(), 0)
                    totalBalanceSP.edit().putInt(ApplCla.BALANCE_VOLCANOS.toString(), totalB + 9999)
                        .apply()
                }

            }

            override fun onError(error: QonversionError) {
                Toast.makeText(
                    this@ShopActivity,
                    "Purchase was unsuccessful. Try Again Later!",
                    Toast.LENGTH_LONG
                ).show()

            }
        })
    }
}