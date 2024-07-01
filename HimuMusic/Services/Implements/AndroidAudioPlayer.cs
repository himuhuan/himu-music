#if ANDROID
using HimuMusic.Models;
using Android.Media;

namespace HimuMusic.Services.Implements
{
    public class AndroidAudioPlayer : IAudioPlayer
    {
        #region Fields
        private MediaPlayer? _mediaPlayer;
        private int currentPositionLength = 0;
        private bool isPrepared;
        private bool isCompleted;
        #endregion

        #region Methods

        public void Play(AudioItem audio)
        {
            if (_mediaPlayer is { IsPlaying: false })
            {
                _mediaPlayer.SeekTo(currentPositionLength);
                currentPositionLength = 0;
                _mediaPlayer.Start();
            }
            else if (_mediaPlayer is not { IsPlaying: true })
            {
                try
                {
                    isCompleted = false;
                    _mediaPlayer = new MediaPlayer();
                    _mediaPlayer.SetDataSource($"http://192.168.1.103:8080/api/music/{audio.Id}/download");
#pragma warning disable CA1422
                    _mediaPlayer.SetAudioStreamType(Android.Media.Stream.Music);
#pragma warning restore CA1422
                    _mediaPlayer.PrepareAsync();
                    _mediaPlayer.Prepared += (sender, args) =>
                    {
                        isPrepared = true;
                        _mediaPlayer.Start();
                    };
                    _mediaPlayer.Completion += (sender, args) =>
                    {
                        isCompleted = true;
                    };
                    // _mediaPlayer.Start();
                }
                catch (Exception)
                {
                    _mediaPlayer = null;
                }
            }
        }

        public void Pause()
        {
            if (_mediaPlayer != null && _mediaPlayer.IsPlaying)
            {
                _mediaPlayer.Pause();
                currentPositionLength = _mediaPlayer.CurrentPosition;
            }
        }

        public void Stop()
        {
            if (_mediaPlayer != null)
            {
                if (isPrepared)
                {
                    _mediaPlayer.Stop();
                    _mediaPlayer.Release();
                    isPrepared = false;
                }
                isCompleted = false;
                _mediaPlayer = null;
            }
        }

        public TimeSpan GetCurrentPosition()
        {
            return TimeSpan.FromMilliseconds(_mediaPlayer?.CurrentPosition ?? 0);
        }

        public void SeekTo(TimeSpan position)
        {
            _mediaPlayer?.SeekTo((int) position.TotalMilliseconds);
        }

        public void SeekTo(int positionSec)
        {
            _mediaPlayer?.SeekTo(positionSec * 1000);
        }

        public bool IsPlaying()
        {
            return _mediaPlayer?.IsPlaying ?? false;
        }

        #endregion
    }
}

#endif