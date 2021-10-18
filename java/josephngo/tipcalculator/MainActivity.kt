package josephngo.tipcalculator

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENTAGE = 15

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var sbTipPercentage: SeekBar
    private lateinit var tvTipPercentLabel: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvTipDescription: TextView
    private lateinit var btLow : Button
    private lateinit var btMed : Button
    private lateinit var btHigh : Button
    private lateinit var etNumSplit : EditText
    private lateinit var tvSplitAmount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBaseAmount = findViewById(R.id.etBaseAmount)
        sbTipPercentage = findViewById(R.id.sbTipPercentage)
        tvTipPercentLabel = findViewById(R.id.tvTipPercentLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvTipDescription = findViewById(R.id.tvTipDescription)
        btLow = findViewById(R.id.btLowTipPreset)
        btMed = findViewById(R.id.btMedTipPreset)
        btHigh = findViewById(R.id.btHighTipPreset)
        etNumSplit = findViewById(R.id.etNumSplit)
        tvSplitAmount = findViewById(R.id.tvSplitAmount)

        sbTipPercentage.progress = INITIAL_TIP_PERCENTAGE
        tvTipPercentLabel.text = "$INITIAL_TIP_PERCENTAGE%"
        updateTipDescription(INITIAL_TIP_PERCENTAGE)

        sbTipPercentage.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged for tip percentage bar: $p1")
                tvTipPercentLabel.text = "$p1%"
                computeTipAndTotal()
                computeSplitTotal()
                updateTipDescription(p1)
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        etBaseAmount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged for base amount: $p0")
                computeTipAndTotal()
                computeSplitTotal()
            }
        })

        btLow.setOnClickListener{
            sbTipPercentage.setProgress(10)
        }
        btMed.setOnClickListener{
            sbTipPercentage.setProgress(15)
        }
        btHigh.setOnClickListener{
            sbTipPercentage.setProgress(20)
        }

        etNumSplit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged for number split: $p0")
                computeSplitTotal()
            }
        })
    }

    private fun computeSplitTotal() {
        if (etNumSplit.text.isEmpty() || tvTotalAmount.text.isEmpty()){
            tvSplitAmount.text = ""
            return
        }

        val baseAmount = tvTotalAmount.text.toString().toDouble()
        val splitNum = etNumSplit.text.toString().toInt()
        if (splitNum == 0){
            tvSplitAmount.text = ""
            return
        }
        val splitAmount = baseAmount / splitNum

        tvSplitAmount.text = "%.2f".format(splitAmount)
    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription = when (tipPercent) {
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            else  -> "Amazing"
        }
        tvTipDescription.text = tipDescription

        val color = ArgbEvaluator().evaluate(
            tipPercent.toFloat() / sbTipPercentage.max,
            ContextCompat.getColor(this, R.color.worstTip),
            ContextCompat.getColor(this, R.color.bestTip)
        ) as Int
        tvTipDescription.setTextColor(color)
    }

    private fun computeTipAndTotal() {
        if (etBaseAmount.text.isEmpty()){
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }
        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent = sbTipPercentage.progress

        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount

        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)
    }
}