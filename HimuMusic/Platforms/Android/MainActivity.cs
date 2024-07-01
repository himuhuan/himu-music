using Android.App;
using Android.Content.PM;
using Android.OS;
using Android.Views;
using AndroidX.Core.View;

namespace HimuMusic
{
    [Activity(Theme = "@style/Maui.SplashTheme",
        MainLauncher = true,
        ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation | ConfigChanges.UiMode | ConfigChanges.ScreenLayout | ConfigChanges.SmallestScreenSize | ConfigChanges.Density)]
    public class MainActivity : MauiAppCompatActivity
    {
        /*
        private class OnApplyWindowInsetsListener : Java.Lang.Object, IOnApplyWindowInsetsListener
        {
            public WindowInsetsCompat OnApplyWindowInsets(Android.Views.View v, WindowInsetsCompat insets)
            {
                var statusBars = insets.GetInsets(WindowInsetsCompat.Type.StatusBars());
                var navigationBars = insets.GetInsets(WindowInsetsCompat.Type.NavigationBars());
                int statusBarHeight = statusBars.Top;
                int navigationBarHeight = navigationBars.Bottom;
                v.SetPadding(0, statusBarHeight, 0, navigationBarHeight);
                return insets;
            }
        }

#pragma warning disable CA1416
        protected override void OnCreate(Bundle? savedInstanceState)
        {
            if (Window != null)
            {
                Google.Android.Material.Internal.EdgeToEdgeUtils.ApplyEdgeToEdge(Window, true);
                if (OperatingSystem.IsAndroidVersionAtLeast((int) BuildVersionCodes.Q))
                {
                    Window.StatusBarContrastEnforced = false;
                    Window.NavigationBarContrastEnforced = false;
                }
                ViewCompat.SetOnApplyWindowInsetsListener(Window.DecorView, new OnApplyWindowInsetsListener());
            }
            base.OnCreate(savedInstanceState);
#pragma warning restore
        }
        */
    }
}