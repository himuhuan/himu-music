using HimuMusic.Services;
using HimuMusic.Services.Implements;
using HimuMusic.States;
using Microsoft.Extensions.Logging;

namespace HimuMusic
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                });

            builder.Services.AddMauiBlazorWebView();
            // modify theme
            builder.Services.AddMasaBlazor(options =>
            {
                options.ConfigureTheme(theme =>
                {
                    theme.Themes.Light.Primary = "#f85a66";
                    theme.Themes.Light.OnPrimary = "#ffffff";

                    theme.Themes.Dark.Primary = "#f85a66";
                    theme.Themes.Dark.OnPrimary = "#ffffff";
                });
            });

            builder.Services.AddScoped<MusicPlayerState>();
            builder.Services.AddHttpClient();
            builder.Services.AddHttpClient<IHimuMusicApiService, HimuMusicApiService>();
            
            builder.Services.AddSingleton<IAudioFilePicker, AudioFilePicker>();
#if ANDROID
            builder.Services.AddSingleton<IAudioPlayer, AndroidAudioPlayer>();
#endif
#if DEBUG
            builder.Services.AddBlazorWebViewDeveloperTools();
    		builder.Logging.AddDebug();
#endif

            return builder.Build();
        }
    }
}
