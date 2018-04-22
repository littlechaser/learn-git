types：描述WebMethod的方法名，入参，回参
portType：一个服务可被执行的操作，即服务对外暴露的接口，一个operation表示一个可调用的方法，即一个接口
message：描述通信的数据结构
git status 查看当前工作区的状态
git add .  # 将所有修改添加到暂存区
git add *  # Ant风格添加修改
git add *.java   # 将以java结尾的文件的所有修改添加到暂存区

git status 查看当前工作区的状态
git commit -a -m 'commit message' 先暂存已经被跟踪的文件，然后一起提交，没暂存的文件不会被提交
git commit --amend -m 'commit message' 把当前提交和上次提交合并为一次提交，上次提交commit_id会被覆盖掉，git log只能看到一次commit，但是修改的内容却不会被覆盖，而是合并
git operation rebase
git cherry-pick
