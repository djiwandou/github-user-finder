package io.gigiperih.githubuser.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.gigiperih.githubuser.databinding.RowUserBinding
import io.gigiperih.githubuser.domain.entity.User
import timber.log.Timber

class UsersAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(RowUserBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(users[position])
    }

    override fun getItemCount() = users.size

    inner class UserViewHolder(
        var binding: RowUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindUser(user: User) {
            binding.textUsername.text = user.userName
            binding.textUrl.text = user.url

            Glide.with(binding.imageThumb.context)
                .load(user.avatar)
                .into(binding.imageThumb)
        }
    }
}