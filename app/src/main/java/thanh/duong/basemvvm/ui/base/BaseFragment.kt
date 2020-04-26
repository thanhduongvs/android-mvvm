package thanh.duong.koinmvvm.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

abstract class BaseFragment : Fragment() {

    fun isAlive(): Boolean = lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
}